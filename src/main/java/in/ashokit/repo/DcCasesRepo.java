package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.DcCasesEntity;


@Repository
public interface DcCasesRepo extends JpaRepository<DcCasesEntity, Serializable> {
	
	public DcCasesEntity findByAppId(Integer appID );

}
