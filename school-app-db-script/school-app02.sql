ALTER TABLE public.act_session_hall RENAME TO act_session_hall_seat;
CREATE SEQUENCE public.act_ticket_id_seq INCREMENT BY 1	MINVALUE 1	MAXVALUE 9223372036854775807 START 1	CACHE 1	NO CYCLE;
ALTER TABLE public.settings ALTER COLUMN value TYPE varchar(1024) USING value::varchar(1024);
ALTER TABLE public.users ADD tckn varchar(11);

