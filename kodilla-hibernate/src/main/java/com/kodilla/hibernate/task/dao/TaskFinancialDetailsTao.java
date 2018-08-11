package com.kodilla.hibernate.task.dao;

import com.kodilla.hibernate.task.TaskFinancialDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TaskFinancialDetailsTao extends CrudRepository<TaskFinancialDetails, Integer> {
List<TaskFinancialDetails> findByPaid (boolean paid);
}
