ALTER TABLE public."user" ALTER COLUMN password TYPE character varying(60);
ALTER TABLE "user" RENAME TO users;