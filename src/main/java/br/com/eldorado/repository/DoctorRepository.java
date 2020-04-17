package br.com.eldorado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eldorado.domain.Doctor;
import br.com.eldorado.domain.User;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>  {

}
