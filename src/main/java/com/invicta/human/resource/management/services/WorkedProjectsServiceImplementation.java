package com.invicta.human.resource.management.services;

import com.invicta.human.resource.management.dto.WorkedProjectDto;
import com.invicta.human.resource.management.entities.WorkExperienceDetails;
import com.invicta.human.resource.management.entities.WorkedProject;
import com.invicta.human.resource.management.repositories.WorkedProjectsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkedProjectsServiceImplementation implements WorkedProjectsService {

  @Autowired
  private WorkedProjectsRepository workedProjectsRepository;

  /**
   * save Worked project details Modified By @author Priyadharshan on 16-11-2022
   *
   * @param workedProjectDto
   */

  @Override
  public void saveWorkedProject(WorkedProjectDto workedProjectDto) {
    WorkExperienceDetails workExperienceDetails = new WorkExperienceDetails();
    workExperienceDetails.setId(workedProjectDto.getWorkExperienceDetailsId());
    WorkedProject workedProject = new WorkedProject();
    BeanUtils.copyProperties(workedProjectDto, workedProject);
    workedProject.setWorkExperienceDetails(workExperienceDetails);
    workedProjectsRepository.save(workedProject);
  }

  /**
   * Delete worked Project by id Modified By @author Priyadharshan on 16-11-2022
   *
   * @param id
   */
  @Override
  public void deleteWorkedProjectById(long id) {
    workedProjectsRepository.deleteById(id);

  }

  /**
   * Get all Worked project by workExperienceDetailsId Modified By @author Priyadharshan on
   * 16-11-2022
   *
   * @param workExperienceDetailsId
   * @return workExperienceDetailsList
   */

  @Override
  public List<WorkedProjectDto> getAllProjectByWorkExperienceDetailsId(
      long workExperienceDetailsId) {
    List<WorkedProjectDto> workedProjectDtoList = new ArrayList<>();
    List<WorkedProject> workedProjectList =
        workedProjectsRepository.findByWorkExperienceDetailsId(workExperienceDetailsId);
    for (WorkedProject workedProject : workedProjectList) {
      WorkedProjectDto workedProjectDto = new WorkedProjectDto();
      BeanUtils.copyProperties(workedProject, workedProjectDto);
      workedProjectDto.setWorkExperienceDetailsId(workedProject.getWorkExperienceDetails().getId());
      workedProjectDtoList.add(workedProjectDto);
    }
    return workedProjectDtoList;
  }

  /**
   * Check exists By WorkExperienceDetailsId Modified By @author Priyadharshan on 16-11-2022
   *
   * @param workExperienceDetailsId
   * @return boolean
   */
  @Override
  public boolean existsByWorkExperienceDetailsId(long workExperienceDetailsId) {
    return workedProjectsRepository.existsByWorkExperienceDetailsId(workExperienceDetailsId);
  }

  /**
   * Check exists By WorkExperienceDetailsId Modified By @author Priyadharshan on 16-11-2022
   *
   * @param id
   * @return boolean
   */

  @Override
  public boolean existsById(long id) {
    return workedProjectsRepository.existsById(id);
  }

  @Override
  public boolean existsByProjectName(String projectName, String role,
      long workExperienceDetailsId) {
    return workedProjectsRepository.existsByProjectNameIgnoreCaseAndRoleIgnoreCaseAndWorkExperienceDetailsId(
        projectName, role, workExperienceDetailsId);
  }

  @Override
  public boolean existsByDesignation(String role, long workExperienceDetailsId) {
    return workedProjectsRepository.existsByRoleIgnoreCaseAndWorkExperienceDetailsId(role,
        workExperienceDetailsId);
  }

  @Override
  public boolean existsByUpdateProjectName(String projectName, String role,
      long workExperienceDetailsId, long id) {
    return workedProjectsRepository.existsByProjectNameIgnoreCaseAndRoleIgnoreCaseAndWorkExperienceDetailsIdAndIdNot(
        projectName, role, workExperienceDetailsId, id);
  }

}
