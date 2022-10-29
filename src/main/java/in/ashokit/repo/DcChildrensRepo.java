package in.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.DcChildrensEntity;


@Repository
public interface DcChildrensRepo extends JpaRepository<DcChildrensEntity, Serializable> {

	public List<DcChildrensEntity> findByCaseNum(Long caseNum);

}
