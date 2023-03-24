package factories.inbound;

import com.loja65.inbound.adapter.dto.CaixaDto;
import com.loja65.inbound.adapter.dto.OperacaoCaixaDto;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CaixaDtoFactory {

    public CaixaDto createBasicCaixaAberto(int localCaixaId, int lojaId){
        List<OperacaoCaixaDto> operacoes = new ArrayList<>();
        operacoes.add(new OperacaoCaixaDto(null, 2020,"ABRIR_CAIXA",100.0,150.0,200.0,LocalDateTime.now()));
        return new CaixaDto(localCaixaId, lojaId, "ABERTO", "Funcionario", LocalDateTime.now(), operacoes);
    }

    public CaixaDto createBasicCaixaFechado(int localCaixaId, int lojaId){
        List<OperacaoCaixaDto> operacoes = new ArrayList<>();
        operacoes.add(new OperacaoCaixaDto(null, 2021,"FECHAR_CAIXA",300.0,150.0,200.0,LocalDateTime.now()));
        return new CaixaDto(localCaixaId, lojaId, "ABERTO", "Funcionario", LocalDateTime.now(), operacoes);
    }
}
