
package com.DuckCompany.finance_manager.repository;

import com.DuckCompany.finance_manager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//defines the repository that Spring DATA JPA will impplement for us
//extends gives us ready made database options
//category : tells spring witch entity this repository manages
//long : tells spring the java type of entitys ID"S
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
