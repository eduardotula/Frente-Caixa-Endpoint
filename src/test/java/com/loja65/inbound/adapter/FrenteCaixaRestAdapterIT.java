package com.loja65.inbound.adapter;

import com.loja65.domain.enums.OperacaoCaixaEnum;
import com.loja65.domain.model.Caixa;
import com.loja65.inbound.adapter.dto.CaixaDto;
import com.loja65.inbound.adapter.dto.OperacaoCaixaDto;
import com.loja65.inbound.adapter.dto.VendaDto;
import com.loja65.inbound.adapter.mappers.OperacaoCaixaDtoMapper;
import com.loja65.outbound.adapters.entity.LojaEntity;
import com.loja65.outbound.adapters.entity.security.UserAuth;
import com.loja65.outbound.adapters.repositories.*;
import com.utils.DatabaseCleaner;
import com.utils.Validator;
import factories.inbound.CaixaDtoFactory;
import factories.inbound.VendaDtoFactory;
import factories.outbound.LojaEntityFactory;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
class FrenteCaixaRestAdapterIT {
    private final String DEFAULT_AUTH_PASS = "pass";
    @Inject
    Validator validator;
    @Inject
    LojaEntityFactory lojaEntityFactory;
    @Inject
    VendaDtoFactory vendaDtoFactory;
    @Inject
    CaixaDtoFactory caixaDtoFactory;
    @Inject
    LojaRepository lojaRepository;
    @Inject
    UserAuthRespository userAuthRespository;
    @Inject
    DatabaseCleaner databaseCleaner;

    private UserAuth defaultUser = new UserAuth("admin",DEFAULT_AUTH_PASS,"admin");

    @BeforeAll
    @Transactional
    void setupAll(){
        lojaRepository.save(lojaEntityFactory.createLoja());
        userAuthRespository.save(defaultUser);
    }

    @BeforeEach
    void setUp() {
        databaseCleaner.cleanVenda();
        databaseCleaner.cleanProduto();
        databaseCleaner.cleanOperacaoCaixa();
        databaseCleaner.cleanCaixa();
    }

    private CaixaDto abrirCaixa(){
        return given().contentType(ContentType.JSON)
                .auth().basic(defaultUser.getUserName(), DEFAULT_AUTH_PASS)
                .body(caixaDtoFactory.createBasicCaixaAberto(2525, 1))
                .post("/caixa/abrir").then().statusCode(200).extract().as(new TypeRef<CaixaDto>() {});
    }

    private CaixaDto fecharCaixa(){
        return given().contentType(ContentType.JSON)
                .auth().basic(defaultUser.getUserName(), DEFAULT_AUTH_PASS)
                .body(caixaDtoFactory.createBasicCaixaAberto(2525, 1))
                .post("/caixa/fechar").then().statusCode(200).extract().as(new TypeRef<CaixaDto>() {});
    }

    private VendaDto saveVenda(CaixaDto caixaDto){
        var vendaActual = vendaDtoFactory.createVenda();
        var response = given()
                .auth().basic(defaultUser.getUserName(), DEFAULT_AUTH_PASS)
                .contentType(ContentType.JSON)
                .body(vendaActual)
                .queryParam("localCaixaId", caixaDto.getLocalCaixaId())
                .post("/venda/ultimoCaixa/" + caixaDto.getLojaId()).then();
        return response.statusCode(200)
                .extract().as(new TypeRef<VendaDto>() {});
    }


    @Nested
    class SaveVendaByLocalCaixaIdAndLojaId{

        @Test
        void saveVendaThenSucess() {
            CaixaDto caixaDto = abrirCaixa();
            var vendaActual = vendaDtoFactory.createVenda();
            var response = given()
                    .auth().basic(defaultUser.getUserName(), DEFAULT_AUTH_PASS)
                    .contentType(ContentType.JSON)
                    .body(vendaActual)
                    .queryParam("localCaixaId", caixaDto.getLocalCaixaId())
                    .post("/venda/ultimoCaixa/" + caixaDto.getLojaId()).then();
            System.out.println(response.extract().asString());

            var venda = response.statusCode(200)
                    .extract().as(new TypeRef<VendaDto>() {});
            validator.validate(vendaActual, venda, "vendaId", "produto.produtoId");
        }

        @Test
        void saveVendaWithAlreadyExistingProduct(){
            CaixaDto caixaDto = abrirCaixa();
            var venda1 = saveVenda(caixaDto);
            var venda2 = vendaDtoFactory.createVenda();
            venda2.getProduto().setDescricao("descricao att");

            var response = given()
                    .auth().basic(defaultUser.getUserName(), DEFAULT_AUTH_PASS)
                    .contentType(ContentType.JSON)
                    .body(venda2)
                    .queryParam("localCaixaId", caixaDto.getLocalCaixaId())
                    .post("/venda/ultimoCaixa/" + caixaDto.getLojaId()).then();
            System.out.println(response.extract().asString());
            venda2 = response.extract().as(new TypeRef<VendaDto>() {});

            validator.validate(venda1, venda2, "vendaId", "createdAt", "produto.descricao");
            Assertions.assertNotEquals(venda1, venda2);
        }
    }

    @Nested
    class CaixaIt{

        @Test
        void openCaixaSaveVendaCloseCaixa(){
            var caixa = abrirCaixa();
            var venda = saveVenda(caixa);
            var caixaFechado = fecharCaixa();
            System.out.println(caixaFechado);

            assertEquals(caixaFechado.getStatus(), Caixa.CaixaStatus.FECHADO);
            assertEquals(caixaFechado.getStatus(), Caixa.CaixaStatus.FECHADO);
            assertEquals(caixaFechado.getOperacaoCaixaDto().get(0).getOperacao(), OperacaoCaixaEnum.ABRIR_CAIXA);
            assertEquals(caixaFechado.getOperacaoCaixaDto().get(1).getOperacao(), OperacaoCaixaEnum.FECHAR_CAIXA);
        }

        @Test
        void sangria(){
            var caixa = abrirCaixa();
            var venda = saveVenda(caixa);

            var response = given().contentType(ContentType.JSON)
                    .auth().basic(defaultUser.getUserName(), DEFAULT_AUTH_PASS)
                    .body(caixaDtoFactory.createBasicCaixaAberto(2525, 1))
                    .post("/caixa/operacaoCaixa/" + caixa.getLojaId()).then().statusCode(200).extract().as(new TypeRef<OperacaoCaixaDto>() {});

            var caixaFechado = fecharCaixa();
        }

    }



}