package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.infra.persistence.ProdutoEntity;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;

public class ImportacaoMapper implements FieldSetMapper<ProdutoEntity> {

    //private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");

    @Override
    public ProdutoEntity mapFieldSet(FieldSet fieldSet) throws BindException {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome(fieldSet.readString("nome"));
        produto.setTipo(fieldSet.readString("tipo"));
        produto.setDescricao(fieldSet.readString("descricao"));
        produto.setValor(fieldSet.readString("valor"));
        produto.setQuantidadeEstoque(fieldSet.readDouble("quantidadeEstoque"));
        produto.setQuantidadeReservada(fieldSet.readDouble("quantidadeReservada"));
        produto.setHoraImportacao(LocalDateTime.now());
        return produto;
    }
}
