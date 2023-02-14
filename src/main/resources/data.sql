---------------------------- superordinate_privilege -------------------------
INSERT INTO superordinate_privilege(
	id, name) VALUES
	(1,'Dashboard'),
    (2,'Employee'),
    (3,'Semita User'),
    (4,'Setting')
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name;

-------------------------- subordinate_privilege ---------------------------
INSERT INTO subordinate_privilege(
    id, name, superordinate_privilege_id)
        VALUES
        (1,'Dashboard',1),
        (2,'Employee',2),
        (3,'Semita User',3),
        (4,'Job Title',4),
        (5,'Designation',4),
        (6,'Employment Category',4),
        (7,'Role Permission',4)
ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name,superordinate_privilege_id=EXCLUDED.superordinate_privilege_id;

--------------------------- permission --------------------------------------
INSERT INTO permission(id, created_at, updated_at, name,Description,subordinate_privilege_id,status)
VALUES

    (1,now(),now(),'DABO','Dashboard',1,true),
    (2,now(),now(),'AE','Add Employee',2,true),
    (3,now(),now(),'GAE','Get All Employee',2,true),
    (4,now(),now(),'GEBI','Get Employee By Id',2,true),
    (5,now(),now(),'UE','Update Employee',2,true),

    ----------------------------Employee Personal Details---------------------
    (7,now(),now(),'APD','Add Personal Details',2,true),
    (8,now(),now(),'UPD','Update Personal Details',2,true),

----------------------------Employee Contact Details---------------------
    (15,now(),now(),'ACD','Add Contact Details',2,true),
    (16,now(),now(),'UCD','Update Contact Details',2,true),

----------------------------Employee Emergency Contact Details---------------------
    (20,now(),now(),'AECD','Add Emergency Contact Details',2,true),
    (21,now(),now(),'UECD','Update Emergency Contact Details',2,true),
    (22,now(),now(),'DECD','Delete Emergency Contact Details',2,true),
    (23,now(),now(),'GECDBI','Get Emergency Contact Details By Id',2,true),

----------------------------Par Details---------------------
    (35,now(),now(),'AParD','Add Par Details',2,true),
    (36,now(),now(),'UParD','Update Par Details',2,true),
    (37,now(),now(),'DParD','Delete Par Details',2,true),

----------------------------Profile Image---------------------
    (40,now(),now(),'API','Add Profile Image',2,true),
    (41,now(),now(),'UPI','Update Profile Image',2,true),
    (42,now(),now(),'GPI','Get Profile Image',2,true),
    (43,now(),now(),'DPI','Delete Profile Image',2,true),

----------------------------BCard Details---------------------
    (45,now(),now(),'ABCD','Add BCard Details',2,true),
    (46,now(),now(),'UBCD','Update BCard Details',2,true),
    (47,now(),now(),'DBCD','Delete BCard Details',2,true),
    (48,now(),now(),'GBCDBI','Get BCard Details By Id',2,true),

----------------------------Education Qualification---------------------
    (55,now(),now(),'AEQ','Add Education Qualification',2,true),
    (56,now(),now(),'UEQ','Update Education Qualification',2,true),
    (57,now(),now(),'DEQ','Delete Education Qualification',2,true),
    (58,now(),now(),'GEQBI','Get Education Qualification By Id',2,true),
    (59,now(),now(),'GAI','Get All Instituion',2,true),

----------------------------Employee Id Card---------------------
    (65,now(),now(),'AEIC','Add Employee Id Card',2,true),
    (66,now(),now(),'UEIC','Update Employee Id Card',2,true),
    (67,now(),now(),'GAEIC','Get All Employee Id Card',2,true),

----------------------------Professional Certificates---------------------
    (70,now(),now(),'APC','Add Professional Certificates',2,true),
    (71,now(),now(),'UPC','Update Professional Certificates',2,true),
    (72,now(),now(),'DPC','Delete Professional Certificates',2,true),
    (73,now(),now(),'GPCBI','Get Professional Certificates By Id',2,true),

----------------------------Promotion Details---------------------
    (75,now(),now(),'AProD','Add Promotion Details',2,true),
    (76,now(),now(),'UProD','Update Promotion Details',2,true),
    (77,now(),now(),'DProD','Delete Promotion Details',2,true),

----------------------------Referees Details---------------------
    (80,now(),now(),'ARD','Add Referees Details',2,true),
    (81,now(),now(),'URD','Update Referees Details',2,true),
    (82,now(),now(),'DRD','Delete Referees Details',2,true),
    (83,now(),now(),'GRDBI','Get Referees Details By Id',2,true),

----------------------------Resignation---------------------
    (85,now(),now(),'AR','Add Resignation',2,true),
    (86,now(),now(),'UR','Update Resignation',2,true),
    (87,now(),now(),'GRBI','Get Resignation By Id',2,true),

----------------------------Vaccination Details---------------------
    (90,now(),now(),'AVD','Add Vaccination Details',2,true),
    (91,now(),now(),'UVD','Update Vaccination Details',2,true),
    (92,now(),now(),'DVD','Delete Vaccination Details',2,true),
    (93,now(),now(),'GVDBI','Get Vaccination Details By Id',2,true),

----------------------------Worked Projects---------------------
    (95,now(),now(),'AWP','Add Worked Projects',2,true),
    (96,now(),now(),'UWP','Update Worked Projects',2,true),
    (97,now(),now(),'DWP','Delete Worked Projects',2,true),
    (98,now(),now(),'WPBWI','Worked Projects By Work Experience Details Id',2,true),

----------------------------Work Experience Details---------------------
    (105,now(),now(),'AWED','Add Work Experience Details',2,true),
    (106,now(),now(),'UWED','Update Work Experience Details',2,true),
    (107,now(),now(),'DWED','Delete Work Experience Details',2,true),
    (108,now(),now(),'GWEBI','Get Work Experience Details By Id',2,true),

----------------------------Exit Procedure---------------------
    (115,now(),now(),'AEP','Add Exit Procedure',2,true),
    (116,now(),now(),'UEP','Update Exit Procedure',2,true),
    (117,now(),now(),'GEPBI','Get All Exit Procedure By Id',2,true),

----------------------------Induction Check List---------------------
    (120,now(),now(),'AICL','Add Induction Check List',2,true),
    (121,now(),now(),'UICL','Update Induction Check List',2,true),
    (122,now(),now(),'GICLBI','Get Induction Check List By Id',2,true),

----------------------------Cabin Key Registry---------------------
    (125,now(),now(),'AKR','Add Key Registry',2,true),
    (126,now(),now(),'UKR','Update Key Registry',2,true),
    (127,now(),now(),'GKRBI','Get Key Registry By Id',2,true),

----------------------------Laptop Allocation---------------------
    (130,now(),now(),'ALA','Add Laptop Allocation',2,true),
    (131,now(),now(),'ULA','Update Laptop Allocation',2,true),
    (132,now(),now(),'GLABI','Get Laptop Allocation By Id',2,true),

----------------------------Defect Tracker (Semita User)---------------------
    (136,now(),now(),'UDT','Update Defect Tracker',3,true),

----------------------------Job Title---------------------
    (140,now(),now(),'AJT','Add Job Title',4,true),
    (141,now(),now(),'UJT','Update Job Title',4,true),
    (142,now(),now(),'GJT','Get Job Title',4,true),
    (143,now(),now(),'DJT','Delete Job Title',4,true),

----------------------------Job Category (Designation)---------------------
    (150,now(),now(),'AD','Add Job Category',5,true),
    (151,now(),now(),'GAD','Get All Designation',5,true),
    (152,now(),now(),'UD','Update Job Category',5,true),
    (153,now(),now(),'DD','Delete Job Category',5,true),
    (154,now(),now(),'GDBI','Get Job Category By Id',5,true),

----------------------------Employee Category---------------------
    (160,now(),now(),'AEC','Add Employment Category',6,true),
    (161,now(),now(),'GEC','Get All Employment Category',6,true),
    (162,now(),now(),'DEC','Delete Employment Category',6,true),
    (163,now(),now(),'GECBI','Get Employment Category By Id',6,true),
    (164,now(),now(),'UEC','Update Employment Category',6,true),

----------------------------Role permission---------------------
    (170,now(),now(),'ARPD','Add Role Permission Details',7,true),
    (171,now(),now(),'GRPGBI','Get Role Permission Details By Id',7,true)

 ON CONFLICT (id) DO UPDATE SET updated_at = EXCLUDED.updated_at,name = EXCLUDED.name,subordinate_privilege_id=EXCLUDED.subordinate_privilege_id;


------------------------------- Allocate role permission for admin ----------------------------------------------

