package factories.outbound;

import com.loja65.frentecaixa.outbound.adapters.entity.LojaEntity;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class LojaEntityFactory {

    public LojaEntity createLoja(){
        return new LojaEntity(null,"Loja teste","07774769000106","rua", "cidade",
                "cep",null,null, LocalDateTime.now(), LocalDateTime.now());
    }
}
