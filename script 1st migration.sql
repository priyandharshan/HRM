
CREATE TABLE employment_category (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   employment_category VARCHAR(255),
   CONSTRAINT pk_employmentcategory PRIMARY KEY (id)
);

CREATE TABLE job_title (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   job_title VARCHAR(255),
   CONSTRAINT pk_jobtitle PRIMARY KEY (id)
);

ALTER TABLE personal_details ADD driving_license_number VARCHAR(255);

ALTER TABLE personal_details ADD license_expiry date;

ALTER TABLE personal_details ADD passport_expiry date;

ALTER TABLE personal_details ADD passport_number VARCHAR(255);

ALTER TABLE employee ADD employment_category_id BIGINT;

ALTER TABLE employee ADD job_title_id BIGINT;

ALTER TABLE employee ADD title VARCHAR(255);

ALTER TABLE bcard_details ADD epf_number BIGINT;

ALTER TABLE induction_check_list ADD is_bcard_given BOOLEAN;

UPDATE
  induction_check_list 
SET
  is_bcard_given = 'false' 
WHERE
  is_bcard_given IS NULL;
ALTER TABLE induction_check_list ALTER COLUMN  is_bcard_given SET NOT NULL;

ALTER TABLE contact_details ADD official_email VARCHAR(255);

ALTER TABLE work_experience_details ADD reason_for_leaving VARCHAR(1000);

ALTER TABLE exit_procedure ADD revoke_old_id BOOLEAN;

UPDATE
  exit_procedure 
SET
  revoke_old_id = 'false' 
WHERE
  revoke_old_id IS NULL;
ALTER TABLE exit_procedure ALTER COLUMN  revoke_old_id SET NOT NULL;

ALTER TABLE employee ADD CONSTRAINT FK_EMPLOYEE_ON_EMPLOYMENTCATEGORY FOREIGN KEY (employment_category_id) REFERENCES employment_category (id);

ALTER TABLE employee ADD CONSTRAINT FK_EMPLOYEE_ON_JOB_TITLE FOREIGN KEY (job_title_id) REFERENCES job_title (id);

ALTER TABLE key_registry DROP COLUMN cabin_available;

ALTER TABLE employee_id_card DROP COLUMN handed_over;

ALTER TABLE employee_id_card DROP COLUMN revoke_old_id;

ALTER TABLE user_credentials DROP COLUMN username;

ALTER TABLE bcard_details ALTER COLUMN approval_rejected_reason TYPE VARCHAR(500) USING (approval_rejected_reason::VARCHAR(500));

ALTER TABLE professional_certificates ALTER COLUMN description TYPE VARCHAR(1000) USING (description::VARCHAR(1000));

ALTER TABLE work_experience_details ALTER COLUMN description TYPE VARCHAR(1000) USING (description::VARCHAR(1000));

ALTER TABLE education_qualification ALTER COLUMN education_qualification_description TYPE VARCHAR(1000) USING (education_qualification_description::VARCHAR(1000));

UPDATE
  contact_details 
SET
  email = 'n/a' 
WHERE
  email IS NULL;
ALTER TABLE contact_details ALTER COLUMN  email SET NOT NULL;

UPDATE
  employee 
SET
  first_name = 'n/a' 
WHERE
  first_name IS NULL;
ALTER TABLE employee ALTER COLUMN  first_name SET NOT NULL;

UPDATE
  personal_details 
SET
  gender = 'Male' 
WHERE
  gender IS NULL;
ALTER TABLE personal_details ALTER COLUMN  gender SET NOT NULL;

UPDATE
  employee 
SET
  last_name = 'n/a' 
WHERE
  last_name IS NULL;
ALTER TABLE employee ALTER COLUMN  last_name SET NOT NULL;

ALTER TABLE vaccination_details ALTER COLUMN location TYPE VARCHAR(500) USING (location::VARCHAR(500));

UPDATE
  personal_details 
SET
  nic_number = 'n/a' 
WHERE
  nic_number IS NULL;
ALTER TABLE personal_details ALTER COLUMN  nic_number SET NOT NULL;

ALTER TABLE contact_details ALTER COLUMN permanent_address TYPE VARCHAR(500) USING (permanent_address::VARCHAR(500));

ALTER TABLE worked_projects ALTER COLUMN project_description TYPE VARCHAR(1000) USING (project_description::VARCHAR(1000));

UPDATE
  worked_projects 
SET
  project_name = 'n/a' 
WHERE
  project_name IS NULL;
ALTER TABLE worked_projects ALTER COLUMN  project_name SET NOT NULL;

ALTER TABLE resignation ALTER COLUMN reason_for_leaving TYPE VARCHAR(1000) USING (reason_for_leaving::VARCHAR(1000));

ALTER TABLE referees_details ALTER COLUMN referee_address TYPE VARCHAR(500) USING (referee_address::VARCHAR(500));

ALTER TABLE laptop_allocation_details ALTER COLUMN remarks TYPE VARCHAR(1000) USING (remarks::VARCHAR(1000));

ALTER TABLE worked_projects ALTER COLUMN technology_used TYPE VARCHAR(1500) USING (technology_used::VARCHAR(1500));

ALTER TABLE contact_details ALTER COLUMN temporary_address TYPE VARCHAR(500) USING (temporary_address::VARCHAR(500));

ALTER TABLE laptop_allocation_details ALTER COLUMN using_location TYPE VARCHAR(500) USING (using_location::VARCHAR(500));

ALTER TABLE employee_id_card DROP CONSTRAINT uk_126yt9ekev4elwyeo65mqvntn;

ALTER TABLE resignation DROP CONSTRAINT uk_13eydpvba2jsd83vrxrs0fkhq;

ALTER TABLE personal_details DROP CONSTRAINT uk_443gon5ex56qlyhka946jrbhi;

ALTER TABLE key_registry DROP CONSTRAINT uk_7myjm09nggjqngw5eipbmmjdb;

ALTER TABLE exit_procedure DROP CONSTRAINT uk_8gfkwbm4qaekjb2es0xor29qa;

ALTER TABLE contact_details DROP CONSTRAINT uk_l1ebcsu2ih5xxtrlb1o17ehlh;

ALTER TABLE induction_check_list DROP CONSTRAINT uk_lvtvi625431k0hjnfevxjyajs;

ALTER TABLE laptop_allocation_details DROP CONSTRAINT uk_or1kn12ghb1977i67im05sh79;

ALTER TABLE user_credentials DROP CONSTRAINT uk_pti0msfxdtvhn2n89njxpn4se;

ALTER TABLE emergency_contact_details DROP CONSTRAINT uk_tqwq0n60drnlxkypyc8w4tysd;

UPDATE public.contact_details	SET mobile_number= '94' || RIGHT (mobile_number, -1)	WHERE mobile_number LIKE '0%';
	
UPDATE public.emergency_contact_details SET phone_number= '94' || RIGHT (phone_number, -1) WHERE phone_number LIKE '0%';
	
UPDATE public.referees_details SET referee_contact_number= '94' || RIGHT (referee_contact_number, -1) WHERE referee_contact_number LIKE '0%';
