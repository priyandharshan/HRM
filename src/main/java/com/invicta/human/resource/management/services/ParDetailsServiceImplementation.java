package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.ParDetailsDto;
import com.invicta.human.resource.management.entities.ParDetails;
import com.invicta.human.resource.management.repositories.ParDetailsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParDetailsServiceImplementation implements ParDetailsService {

  @Autowired
  ParDetailsRepository parDetailsRepository;

  /**
   * Saves PAR details into the database
   *
   * @param parDetails - PAR details
   * @author Dhivyaruban
   * @date 27-10-2022
   */
  @Override
  public void saveParDetails(ParDetails parDetails) {
    parDetailsRepository.save(parDetails);
  }

  /**
   * Gets all PAR details
   *
   * @return PAR detail Dtos
   * @author Dhivyaruban
   * @date 27-10-2022
   */
  @Override
  public List<ParDetailsDto> getParDetailsDtos() {
    List<ParDetailsDto> parDetailsDtos = new ArrayList<>();
    List<ParDetails> parDetailsList = parDetailsRepository.findAll();

    for (ParDetails parDetails : parDetailsList) {
      ParDetailsDto parDetailsDto = new ParDetailsDto();
      BeanUtils.copyProperties(parDetails, parDetailsDto);
      parDetailsDtos.add(parDetailsDto);
    }

    return parDetailsDtos;
  }

  /**
   * Gets PAR details and converts to dto
   *
   * @param id - Employee id
   * @return Par details Dto
   * @author Dhivyaruban
   * @date 27-10-2022
   */
  @Override
  public ParDetailsDto getParDetailsDto(String id) {
    ParDetails parDetails = parDetailsRepository.findByEmployeeId(id).get();
    ParDetailsDto parDetailsDto = new ParDetailsDto();
    BeanUtils.copyProperties(parDetails, parDetailsDto);
    parDetailsDto.setId(parDetails.getId());
    return parDetailsDto;
  }

  /**
   * Updates PAR details
   *
   * @param parDetails - ParDetails object
   * @author Dhivyaruban
   * @date 28-10-2022
   */
  @Override
  public void updateParDetails(ParDetails parDetails) {
    parDetailsRepository.save(parDetails);
  }

  /**
   * Deletes PAR details
   *
   * @param id - Employee id
   * @author Dhivyaruban
   * @date 28-10-2022
   */

  @Transactional
  public void deleteParDetailsById(long id) {
    parDetailsRepository.deleteById(id);
  }

}
