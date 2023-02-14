package com.invicta.human.resource.management.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.invicta.human.resource.management.dto.SearchDesiginationDto;
import com.invicta.human.resource.management.entities.Designation;
import com.invicta.human.resource.management.response.PaginatedContentResponse.Pagination;
import com.invicta.human.resource.management.responseDto.DesiginationResponseDto;

public interface DesignationService {

	void saveDesignation(Designation designation);

	List<Designation> getAllDesignation();

	Object getById(Long id);

	void deleteDesignation(Long id);

	boolean isDesignation(String designation);

	void updateDesignation(Designation designation);

	boolean existsByDesignationId(Long id);

	Designation getDesignationById(Long id);

	List<DesiginationResponseDto> searchDesiginationPagination(SearchDesiginationDto searchDesiginationDto,
			Pageable pageable, Pagination pagination);

	boolean updateDesignationExists(String designation, long id);

    boolean isIdExists(Long roleId);
}
