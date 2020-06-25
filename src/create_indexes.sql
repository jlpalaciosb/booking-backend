-- Important script to create indexes in the database to speed up some queries.
-- Running correctly in: PostgreSQL 11.

create index service_name_index on service (name);
create index service_upper_name_index on service (upper(name));

create index client_document_index on client (document);
create index client_first_name_index on client (first_name);
create index client_last_name_index on client (last_name);
create index client_upper_last_name_index on client (upper(last_name));

create index professional_document_index on professional (document);
create index professional_first_name_index on professional (first_name);
create index professional_last_name_index on professional (last_name);
create index professional_upper_last_name_index on professional (upper(last_name));

create index appointment_date_start_time_index on appointment (date, start_time);
create index appointment_date_index on appointment (date);
create index appointment_client_id_index on appointment (client_id);

create index professional_services_professional_id_index on professional_services (professional_id);
create index professional_services_services_id_index on professional_services (services_id);

/*
Drop indexes
drop index if exists service_name_index;
drop index if exists service_upper_name_index;

drop index if exists client_document_index;
drop index if exists client_first_name_index;
drop index if exists client_last_name_index;
drop index if exists client_upper_last_name_index;

drop index if exists professional_document_index;
drop index if exists professional_first_name_index;
drop index if exists professional_last_name_index;
drop index if exists professional_upper_last_name_index;

drop index if exists appointment_date_start_time_index;
drop index if exists appointment_date_index;
drop index if exists appointment_client_id_index;

drop index if exists professional_services_professional_id_index;
drop index if exists professional_services_services_id_index;
*/
