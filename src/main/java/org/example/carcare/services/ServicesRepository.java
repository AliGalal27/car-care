package org.example.carcare.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {

    Optional<Services> findByServiceid(int service_id);

    List<Services> findByServiceType(String service_type);
    @Query("SELECT DISTINCT s.serviceType FROM Services s")
    List<String> findDistinctServiceTypes();

}
