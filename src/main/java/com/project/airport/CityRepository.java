


/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */
package com.project.airport;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;

// CrudRepository provides save .. find deklete and a find all
// PagingAndSortingRepository adds findAll(Pageable object that is made) which SHOULD return that Page.
// Extending both keeps normal CRUD methods and adds paging support.
public interface CityRepository extends CrudRepository<City, Long>, PagingAndSortingRepository<City, Long> {
}