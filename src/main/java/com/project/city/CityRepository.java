


/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */
package com.project.city;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


//Pagination was added to the City endpoint because cities are reference data that grows over time



// CrudRepository provides save .. find deklete and a find all
// PagingAndSortingRepository adds findAll(Pageable object that is made) which SHOULD return that Page.
// Extending both keeps normal CRUD methods and adds paging support.
public interface CityRepository extends CrudRepository<City, Long>, PagingAndSortingRepository<City, Long> {
}