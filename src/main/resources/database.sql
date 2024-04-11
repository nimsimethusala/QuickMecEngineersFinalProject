create database QuickMecEngineers;

use QuickMecEngineers;

create table customer(
                         customer_id varchar(6)primary key,
                         customer_name varchar(25),
                         address varchar(20),
                         contact varchar(10)
);

insert into customer values('C001','Kamal','Panadura',0775648952);
insert into customer values('C002','Namal','Galle',0775679952);
insert into customer values('C003','Amal','Piiliyandala',0772456871);

create table job(
                    job_No varchar(6)primary key,
                    Model varchar(25),
                    Date date,
                    customer_id varchar(6),
                    foreign key(customer_id) references customer(customer_id) on update cascade on delete cascade
);

insert into job values('J001','Lorry','2022/10/25','C001');
insert into job values('J002','Bus','2022/10/28','C002');
insert into job values('J003','Car','2022/11/05','C003');

create table item(
                     item_No varchar(6)primary key,
                     Name varchar(25),
                     Item_count int(20),
                     job_No varchar(6),
                     foreign key(job_No)references job(job_No) on update cascade on delete cascade
);

insert into item values('I001','Piston',2,'J001');
insert into item values('I002','Piston',2,'J002');
insert into item values('I003','Piston',2,'J003');

create table defect(
                       defect_id varchar(6)primary key,
                       description varchar(25)
);

insert into defect values('D001','reduce piston from top');
insert into defect values('D002','removing and refitting cylinder liner')

create table item_defect_details(
                                    item_No varchar(6),
                                    foreign key(item_No)references item(item_No)on update cascade on delete cascade,
                                    defect_id varchar(6),
                                    foreign key(defect_id)references defect(defect_id) on update cascade on delete cascade
);

insert into item_defect_details values('I001','D001');
insert into item_defect_details values('I002','D002');

create table employee(
                         Emp_id varchar(6)primary key,
                         Name varchar(25),
                         attendence int(5),
                         address varchar(20),
                         contact varchar(10)
);

create table spare(
                      Spare_id varchar(6)primary key,
                      Name varchar(25),
                      type varchar(20),
                      contact varchar(10)
);

create table supplier(
                         supplier_id varchar(6),
                         name varchar(25),
                         location varchar(20),
                         contact int(10)
);

create table machine(
                        machine_id varchar(6),
                        date date,
                        Emp_id varchar(6),
                        foreign key(Emp_id)references employee(Emp_id)on update cascade on delete cascade
);
