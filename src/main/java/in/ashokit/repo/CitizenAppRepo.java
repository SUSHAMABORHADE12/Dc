package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.CitizenAppEntity;

@Repository
public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Integer> {

}
