create database QuickMecEngineers;

use QuickMecEngineers;

create table customer(
                         customer_id varchar(6)primary key,
                         customer_name varchar(25),
                         address varchar(20),
                         contact varchar(10)
);

create table job(
                    job_No varchar(6)primary key,
                    Model varchar(25),
                    Date date,
                    customer_id varchar(6),
                    foreign key(customer_id) references customer(customer_id) on update cascade on delete cascade
);

create table item(
                     item_No varchar(6)primary key,
                     Name varchar(25),
                     Item_count int(20),
                     job_No varchar(6),
                     foreign key(job_No)references job(job_No) on update cascade on delete cascade
);

create table defect(
                       defect_id varchar(6)primary key,
                       description varchar(25)
);

create table item_defect_details(
                                    item_No varchar(6),
                                    foreign key(item_No)references item(item_No)on update cascade on delete cascade,
                                    defect_id varchar(6),
                                    foreign key(defect_id)references defect(defect_id) on update cascade on delete cascade
);

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
                         supplier_id varchar(6)primary key ,
                         name varchar(25),
                         location varchar(20),
                         contact int(10)
);

create table machine(
                        machine_id varchar(6)primary key ,
                        date date,
                        Emp_id varchar(6),
                        foreign key(Emp_id)references employee(Emp_id)on update cascade on delete cascade
);

create table credential(
                        username varchar(25),
                        password varchar(10)

);

insert into credential values ('nimsi', '123');