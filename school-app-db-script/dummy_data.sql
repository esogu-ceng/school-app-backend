INSERT INTO public.users (name, surname, mail, password) VALUES
('admin', 'admin', 'admin', '$2y$10$dwLP0SW5hoVMbEcHY1Dfs.15ukAPEB6ZyZJyTZTFZs88dNYiZpNH2');

INSERT INTO public.student (name, surname, grade) VALUES
('John', 'Doe', 10),
('Jane', 'Doe', 11),
('Mike', 'Smith', 9),
('Emily', 'Jones', 12);

INSERT INTO public.student_user (user_id, student_id) VALUES
(1, 2),
(1, 3);

INSERT INTO public.term (term_name, start_date, end_date) VALUES
('2023 Fall', '2023-09-01', '2024-01-31'),
('2024 Spring', '2024-02-01', '2024-06-30');

INSERT INTO public.student_term (student_id, term_id) VALUES
(1, 2),
(2, 1);

INSERT INTO public.payment (amount, payment_date) VALUES
(1000.0, '2023-09-05'),
(500.0, '2023-09-15');

INSERT INTO public.installment (amount, due_date, term_id, student_id, payment_id) VALUES
(1000.0, '2023-09-30', 1, 1, 1),
(500.0, '2023-10-31', 1, 1, 2);