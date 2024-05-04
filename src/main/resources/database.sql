create database QuickMecEngineers;

use QuickMecEngineers;

create table customer(
                         customer_id varchar(6)primary key,
                         customer_name varchar(25),
                         address varchar(20),
                         contact varchar(10)
);

create table defect(
                       defect_id varchar(6)primary key,
                       description varchar(25),
                       price varchar(10)
);

create table spare(
                      Spare_id varchar(6)primary key,
                      Name varchar(25),
                      count varchar(10),
                      price varchar(10)
);

create table job(
                    job_No varchar(6)primary key,
                    Model varchar(25),
                    Date date,
                    customer_id varchar(6),
                    foreign key(customer_id) references customer(customer_id) on update cascade on delete cascade,
                    item_count varchar(10),
                    defect_id varchar(6),
                    foreign key(defect_id) references defect(defect_id) on update cascade on delete cascade,
                    Spare_id varchar(6),
                    foreign key(Spare_id) references spare(Spare_id) on update cascade on delete cascade,
                    Spare_count varchar(10),
                    Employee_id varchar(10)
);

create table item(
                     item_No varchar(6)primary key,
                     Name varchar(25),
                     Item_count int(20)
);

create table payment(
                    payment_id  varchar(10),
                    job_No varchar(6),
                    foreign key(job_No) references job(job_No) on update cascade on delete cascade,
                    defect_total_cost varchar(15),
                    employee_total_cost varchar(15),
                    spare_total_cost varchar(15),
                    total_cost varchar(20)
);

create table job_details(
                                    item_No varchar(6),
                                    foreign key(item_No)references item(item_No)on update cascade on delete cascade,
                                    item_count varchar(10),
                                    foreign key(item_count)references job(item_count)on update cascade on delete cascade,
                                    Model varchar(25),
                                    job_No varchar(6),
                                    foreign key(job_No)references job(job_No) on update cascade on delete cascade
);

create table employee(
                         Emp_id varchar(6)primary key,
                         Name varchar(25),
                         attendence int(5),
                         address varchar(20),
                         contact varchar(10),
                         salary varchar(10),
                         cost varchar(10)
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

create table spare_supplier_detail(
                        Spare_id varchar(6),
                        foreign key(Spare_id)references spare(Spare_id)on update cascade on delete cascade,
                        supplier_id varchar(6),
                        foreign key(supplier_id)references supplier(supplier_id)on update cascade on delete cascade
);

create table credential(
                        username varchar(25),
                        password varchar(10)

);

insert into credential values ('nimsi', '123');