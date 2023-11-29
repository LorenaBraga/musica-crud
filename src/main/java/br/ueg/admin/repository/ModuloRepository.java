package br.ueg.admin.repository;

import br.ueg.admin.model.ModuloModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuloRepository extends JpaRepository<ModuloModel, Long> {

    /**
     * Retorna uma lista de {@link ModuloModel} ativos conforme o 'id' do Sistema informado.
     *
     * @return
     */
    @Query(" SELECT DISTINCT modulo FROM ModuloModel modulo "
            + " INNER JOIN FETCH modulo.funcionalidades funcionalidades"
            + " WHERE modulo.status = 'A'"
            + " ORDER BY modulo.nome ASC, funcionalidades.nome ASC ")
    public List<ModuloModel> getAtivos();
}
