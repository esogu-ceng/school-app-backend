ALTER TABLE public."user" ALTER COLUMN password TYPE character varying(72);
ALTER TABLE "user" RENAME TO users;