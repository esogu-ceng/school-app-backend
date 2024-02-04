
--
-- TOC entry 212 (class 1259 OID 210500)
-- Name: act_category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.act_category (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


--
-- TOC entry 211 (class 1259 OID 210499)
-- Name: act_category_idcolumn1_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.act_category ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.act_category_idcolumn1_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 210510)
-- Name: act_hall; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.act_hall (
    id bigint NOT NULL,
    name character varying(100) NOT NULL
);


--
-- TOC entry 210 (class 1259 OID 210496)
-- Name: act_seat; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.act_seat (
    id bigint NOT NULL,
    line character varying(2) NOT NULL,
    no integer NOT NULL,
    category_id bigint NOT NULL,
    hall_id bigint NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 210495)
-- Name: act_hall_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

ALTER TABLE public.act_seat ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.act_hall_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 210528)
-- Name: act_session_hall; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.act_session_hall (
    id bigint NOT NULL,
    session_id bigint NOT NULL,
    seat_id bigint NOT NULL,
    status character varying(50) NOT NULL,
    user_id bigint,
    blocked_time date
);


--
-- TOC entry 213 (class 1259 OID 210507)
-- Name: act_session_info; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.act_session_info (
    id bigint NOT NULL,
    hall_id bigint NOT NULL,
    activity_name character varying(255) NOT NULL,
    activity_date date NOT NULL,
    fee double precision
);


--
-- TOC entry 215 (class 1259 OID 210517)
-- Name: act_ticket; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.act_ticket (
    id bigint NOT NULL,
    filepath character varying(512) NOT NULL,
    status character varying(60) NOT NULL,
    verification_code character varying(255) NOT NULL,
    user_id bigint NOT NULL,
    session_hall_id bigint NOT NULL,
    session_date date DEFAULT CURRENT_DATE NOT NULL
);


--
-- TOC entry 226 (class 1259 OID 210980)
-- Name: installment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.installment (
    id integer NOT NULL,
    amount double precision NOT NULL,
    due_date date NOT NULL,
    term_id integer NOT NULL,
    student_id integer NOT NULL,
    payment_id integer
);


--
-- TOC entry 225 (class 1259 OID 210979)
-- Name: installment_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.installment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3447 (class 0 OID 0)
-- Dependencies: 225
-- Name: installment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.installment_id_seq OWNED BY public.installment.id;


--
-- TOC entry 224 (class 1259 OID 210973)
-- Name: payment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.payment (
    id integer NOT NULL,
    amount double precision NOT NULL,
    payment_date date NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 210972)
-- Name: payment_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.payment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3448 (class 0 OID 0)
-- Dependencies: 223
-- Name: payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.payment_id_seq OWNED BY public.payment.id;


--
-- TOC entry 230 (class 1259 OID 211042)
-- Name: settings; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.settings (
    id integer NOT NULL,
    key character varying(50) NOT NULL,
    value character varying(50),
    create_date timestamp with time zone,
    update_date timestamp with time zone
);


--
-- TOC entry 229 (class 1259 OID 211041)
-- Name: settings_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.settings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 229
-- Name: settings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.settings_id_seq OWNED BY public.settings.id;


--
-- TOC entry 220 (class 1259 OID 210959)
-- Name: student; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.student (
    id integer NOT NULL,
    name character varying(32) NOT NULL,
    surname character varying(32) NOT NULL,
    grade integer NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 210958)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.student_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 219
-- Name: student_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.student_id_seq OWNED BY public.student.id;


--
-- TOC entry 228 (class 1259 OID 211016)
-- Name: student_term; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.student_term (
    student_id integer NOT NULL,
    term_id integer NOT NULL
);


--
-- TOC entry 227 (class 1259 OID 211001)
-- Name: student_user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.student_user (
    user_id integer NOT NULL,
    student_id integer NOT NULL
);


--
-- TOC entry 222 (class 1259 OID 210966)
-- Name: term; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.term (
    id integer NOT NULL,
    term_name character varying(30) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);


--
-- TOC entry 221 (class 1259 OID 210965)
-- Name: term_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.term_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 221
-- Name: term_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.term_id_seq OWNED BY public.term.id;


--
-- TOC entry 218 (class 1259 OID 210950)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    name character varying(32) NOT NULL,
    surname character varying(32) NOT NULL,
    mail character varying(30),
    password character varying(15) NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 210949)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- TOC entry 3231 (class 2604 OID 210983)
-- Name: installment id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.installment ALTER COLUMN id SET DEFAULT nextval('public.installment_id_seq'::regclass);


--
-- TOC entry 3230 (class 2604 OID 210976)
-- Name: payment id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payment ALTER COLUMN id SET DEFAULT nextval('public.payment_id_seq'::regclass);


--
-- TOC entry 3232 (class 2604 OID 211045)
-- Name: settings id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.settings ALTER COLUMN id SET DEFAULT nextval('public.settings_id_seq'::regclass);


--
-- TOC entry 3228 (class 2604 OID 210962)
-- Name: student id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student ALTER COLUMN id SET DEFAULT nextval('public.student_id_seq'::regclass);


--
-- TOC entry 3229 (class 2604 OID 210969)
-- Name: term id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.term ALTER COLUMN id SET DEFAULT nextval('public.term_id_seq'::regclass);


--
-- TOC entry 3227 (class 2604 OID 210953)
-- Name: user id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- TOC entry 3422 (class 0 OID 210500)
-- Dependencies: 212
-- Data for Name: act_category; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.act_category OVERRIDING SYSTEM VALUE VALUES (5, 'Salon');
INSERT INTO public.act_category OVERRIDING SYSTEM VALUE VALUES (6, 'Balkon');


--
-- TOC entry 3424 (class 0 OID 210510)
-- Dependencies: 214
-- Data for Name: act_hall; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.act_hall VALUES (1, 'Zübeyde Hanım Kültür Merkezi');


--
-- TOC entry 3420 (class 0 OID 210496)
-- Dependencies: 210
-- Data for Name: act_seat; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (8, 'A', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (9, 'A', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (10, 'A', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (11, 'A', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (12, 'A', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (13, 'A', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (14, 'A', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (15, 'A', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (16, 'A', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (17, 'A', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (18, 'A', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (19, 'A', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (20, 'A', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (21, 'A', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (22, 'A', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (23, 'A', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (24, 'B', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (25, 'B', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (26, 'B', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (27, 'B', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (28, 'B', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (29, 'B', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (30, 'B', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (31, 'B', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (32, 'B', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (33, 'B', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (34, 'B', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (35, 'B', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (36, 'B', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (37, 'B', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (38, 'B', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (39, 'C', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (40, 'C', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (41, 'C', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (42, 'C', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (43, 'C', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (44, 'C', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (45, 'C', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (46, 'C', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (47, 'C', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (48, 'C', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (49, 'C', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (50, 'C', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (51, 'C', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (52, 'C', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (53, 'C', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (54, 'D', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (55, 'D', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (56, 'D', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (57, 'D', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (58, 'D', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (59, 'D', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (60, 'D', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (61, 'D', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (62, 'D', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (63, 'D', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (64, 'D', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (65, 'D', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (66, 'D', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (67, 'D', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (68, 'D', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (69, 'E', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (70, 'E', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (71, 'E', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (72, 'E', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (73, 'E', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (74, 'E', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (75, 'E', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (76, 'E', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (77, 'E', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (78, 'E', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (79, 'E', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (80, 'E', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (81, 'E', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (82, 'E', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (83, 'E', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (84, 'E', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (85, 'E', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (86, 'F', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (87, 'F', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (88, 'F', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (89, 'F', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (90, 'F', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (91, 'F', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (92, 'F', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (93, 'F', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (94, 'F', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (95, 'F', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (96, 'F', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (97, 'F', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (98, 'F', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (99, 'F', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (100, 'F', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (101, 'F', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (102, 'F', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (103, 'F', 18, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (104, 'G', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (105, 'G', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (106, 'G', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (107, 'G', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (108, 'G', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (109, 'G', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (110, 'G', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (111, 'G', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (112, 'G', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (113, 'G', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (114, 'G', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (115, 'G', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (116, 'G', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (117, 'G', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (118, 'G', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (119, 'G', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (120, 'G', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (121, 'G', 18, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (122, 'H', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (123, 'H', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (124, 'H', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (125, 'H', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (126, 'H', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (127, 'H', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (128, 'H', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (129, 'H', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (130, 'H', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (131, 'H', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (132, 'H', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (133, 'H', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (134, 'H', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (135, 'H', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (136, 'H', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (137, 'H', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (138, 'H', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (139, 'I', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (140, 'I', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (141, 'I', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (142, 'I', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (143, 'I', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (144, 'I', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (145, 'I', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (146, 'I', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (147, 'I', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (148, 'I', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (149, 'I', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (150, 'I', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (151, 'I', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (152, 'I', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (153, 'I', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (154, 'I', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (155, 'I', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (156, 'I', 18, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (157, 'J', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (158, 'J', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (159, 'J', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (160, 'J', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (161, 'J', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (162, 'J', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (163, 'J', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (164, 'J', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (165, 'J', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (166, 'J', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (167, 'J', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (168, 'J', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (169, 'J', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (170, 'J', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (171, 'J', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (172, 'J', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (173, 'J', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (174, 'J', 18, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (175, 'J', 19, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (176, 'K', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (177, 'K', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (178, 'K', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (179, 'K', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (180, 'K', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (181, 'K', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (182, 'K', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (183, 'K', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (184, 'K', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (185, 'K', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (186, 'K', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (187, 'K', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (188, 'K', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (189, 'K', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (190, 'K', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (191, 'K', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (192, 'K', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (193, 'K', 18, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (194, 'L', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (195, 'L', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (196, 'L', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (197, 'L', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (198, 'L', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (199, 'L', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (200, 'L', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (201, 'L', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (202, 'L', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (203, 'L', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (204, 'L', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (205, 'L', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (206, 'L', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (207, 'L', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (208, 'L', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (209, 'M', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (210, 'M', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (211, 'M', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (212, 'M', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (213, 'M', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (214, 'M', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (215, 'M', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (216, 'M', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (217, 'M', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (218, 'M', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (219, 'M', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (220, 'M', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (221, 'M', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (222, 'M', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (223, 'M', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (224, 'M', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (225, 'N', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (226, 'N', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (227, 'N', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (228, 'N', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (229, 'N', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (230, 'N', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (231, 'N', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (232, 'N', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (233, 'N', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (234, 'N', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (235, 'N', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (236, 'N', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (237, 'N', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (238, 'N', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (239, 'N', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (240, 'O', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (241, 'O', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (242, 'O', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (243, 'O', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (244, 'O', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (245, 'O', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (246, 'O', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (247, 'O', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (248, 'O', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (249, 'O', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (250, 'O', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (251, 'O', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (252, 'O', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (253, 'O', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (254, 'O', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (255, 'O', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (256, 'P', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (257, 'P', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (258, 'P', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (259, 'P', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (260, 'P', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (261, 'P', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (262, 'P', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (263, 'P', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (264, 'P', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (265, 'P', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (266, 'P', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (267, 'P', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (268, 'P', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (269, 'P', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (270, 'P', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (271, 'P', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (272, 'R', 1, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (273, 'R', 2, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (274, 'R', 3, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (275, 'R', 4, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (276, 'R', 5, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (277, 'R', 6, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (278, 'R', 7, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (279, 'R', 8, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (280, 'R', 9, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (281, 'R', 10, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (282, 'R', 11, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (283, 'R', 12, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (284, 'R', 13, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (285, 'R', 14, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (286, 'R', 15, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (287, 'R', 16, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (288, 'R', 17, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (289, 'R', 18, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (290, 'R', 19, 5, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (291, 'BA', 21, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (292, 'BA', 20, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (293, 'BA', 19, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (294, 'BA', 18, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (295, 'BA', 17, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (296, 'BA', 16, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (297, 'BA', 15, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (298, 'BA', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (299, 'BA', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (300, 'BA', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (301, 'BA', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (302, 'BA', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (303, 'BA', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (304, 'BA', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (305, 'BA', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (306, 'BA', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (307, 'BA', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (308, 'BA', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (309, 'BA', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (310, 'BA', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (311, 'BA', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (312, 'BB', 20, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (313, 'BB', 19, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (314, 'BB', 18, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (315, 'BB', 17, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (316, 'BB', 16, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (317, 'BB', 15, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (318, 'BB', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (319, 'BB', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (320, 'BB', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (321, 'BB', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (322, 'BB', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (323, 'BB', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (324, 'BB', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (325, 'BB', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (326, 'BB', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (327, 'BB', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (328, 'BB', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (329, 'BB', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (330, 'BB', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (331, 'BB', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (332, 'BC', 19, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (333, 'BC', 18, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (334, 'BC', 17, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (335, 'BC', 16, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (336, 'BC', 15, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (337, 'BC', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (338, 'BC', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (339, 'BC', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (340, 'BC', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (341, 'BC', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (342, 'BC', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (343, 'BC', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (344, 'BC', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (345, 'BC', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (346, 'BC', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (347, 'BC', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (348, 'BC', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (349, 'BC', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (350, 'BC', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (351, 'BD', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (352, 'BD', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (353, 'BD', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (354, 'BD', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (355, 'BD', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (356, 'BD', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (357, 'BD', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (358, 'BD', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (359, 'BD', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (360, 'BD', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (361, 'BD', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (362, 'BD', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (363, 'BD', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (364, 'BD', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (365, 'BE', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (366, 'BE', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (367, 'BE', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (368, 'BE', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (369, 'BE', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (370, 'BE', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (371, 'BE', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (372, 'BE', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (373, 'BE', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (374, 'BE', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (375, 'BE', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (376, 'BE', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (377, 'BE', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (378, 'BF', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (379, 'BF', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (380, 'BF', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (381, 'BF', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (382, 'BF', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (383, 'BF', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (384, 'BF', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (385, 'BF', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (386, 'BF', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (387, 'BF', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (388, 'BF', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (389, 'BF', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (390, 'BF', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (391, 'BF', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (392, 'BG', 16, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (393, 'BG', 15, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (394, 'BG', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (395, 'BG', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (396, 'BG', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (397, 'BG', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (398, 'BG', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (399, 'BG', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (400, 'BG', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (401, 'BG', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (402, 'BG', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (403, 'BG', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (404, 'BG', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (405, 'BG', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (406, 'BG', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (407, 'BG', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (408, 'BH', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (409, 'BH', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (410, 'BH', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (411, 'BH', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (412, 'BH', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (413, 'BH', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (414, 'BH', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (415, 'BH', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (416, 'BH', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (417, 'BH', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (418, 'BH', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (419, 'BH', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (420, 'BH', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (421, 'BH', 1, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (422, 'BI', 16, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (423, 'BI', 15, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (424, 'BI', 14, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (425, 'BI', 13, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (426, 'BI', 12, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (427, 'BI', 11, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (428, 'BI', 10, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (429, 'BI', 9, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (430, 'BI', 8, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (431, 'BI', 7, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (432, 'BI', 6, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (433, 'BI', 5, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (434, 'BI', 4, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (435, 'BI', 3, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (436, 'BI', 2, 6, 1);
INSERT INTO public.act_seat OVERRIDING SYSTEM VALUE VALUES (437, 'BI', 1, 6, 1);


--
-- TOC entry 3426 (class 0 OID 210528)
-- Dependencies: 216
-- Data for Name: act_session_hall; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.act_session_hall VALUES (1, 1, 8, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (2, 1, 9, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (3, 1, 10, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (4, 1, 11, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (5, 1, 12, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (6, 1, 13, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (7, 1, 14, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (8, 1, 15, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (9, 1, 16, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (10, 1, 17, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (11, 1, 18, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (12, 1, 19, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (13, 1, 20, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (14, 1, 21, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (15, 1, 22, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (16, 1, 23, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (17, 1, 24, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (18, 1, 25, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (19, 1, 26, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (20, 1, 27, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (21, 1, 28, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (22, 1, 29, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (23, 1, 30, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (24, 1, 31, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (25, 1, 32, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (26, 1, 33, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (27, 1, 34, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (28, 1, 35, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (29, 1, 36, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (30, 1, 37, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (31, 1, 38, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (32, 1, 39, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (33, 1, 40, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (34, 1, 41, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (35, 1, 42, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (36, 1, 43, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (37, 1, 44, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (38, 1, 45, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (39, 1, 46, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (40, 1, 47, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (41, 1, 48, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (42, 1, 49, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (43, 1, 50, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (44, 1, 51, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (45, 1, 52, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (46, 1, 53, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (47, 1, 54, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (48, 1, 55, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (49, 1, 56, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (50, 1, 57, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (51, 1, 58, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (52, 1, 59, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (53, 1, 60, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (54, 1, 61, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (55, 1, 62, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (56, 1, 63, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (57, 1, 64, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (58, 1, 65, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (59, 1, 66, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (60, 1, 67, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (61, 1, 68, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (62, 1, 69, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (63, 1, 70, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (64, 1, 71, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (65, 1, 72, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (66, 1, 73, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (67, 1, 74, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (68, 1, 75, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (69, 1, 76, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (70, 1, 77, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (71, 1, 78, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (72, 1, 79, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (73, 1, 80, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (74, 1, 81, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (75, 1, 82, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (76, 1, 83, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (77, 1, 84, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (78, 1, 85, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (79, 1, 86, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (80, 1, 87, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (81, 1, 88, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (82, 1, 89, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (83, 1, 90, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (84, 1, 91, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (85, 1, 92, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (86, 1, 93, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (87, 1, 94, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (88, 1, 95, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (89, 1, 96, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (90, 1, 97, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (91, 1, 98, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (92, 1, 99, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (93, 1, 100, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (94, 1, 101, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (95, 1, 102, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (96, 1, 103, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (97, 1, 104, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (98, 1, 105, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (99, 1, 106, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (100, 1, 107, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (101, 1, 108, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (102, 1, 109, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (103, 1, 110, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (104, 1, 111, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (105, 1, 112, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (106, 1, 113, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (107, 1, 114, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (108, 1, 115, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (109, 1, 116, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (110, 1, 117, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (111, 1, 118, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (112, 1, 119, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (113, 1, 120, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (114, 1, 121, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (115, 1, 122, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (116, 1, 123, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (117, 1, 124, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (118, 1, 125, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (119, 1, 126, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (120, 1, 127, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (121, 1, 128, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (122, 1, 129, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (123, 1, 130, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (124, 1, 131, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (125, 1, 132, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (126, 1, 133, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (127, 1, 134, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (128, 1, 135, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (129, 1, 136, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (130, 1, 137, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (131, 1, 138, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (132, 1, 139, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (133, 1, 140, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (134, 1, 141, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (135, 1, 142, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (136, 1, 143, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (137, 1, 144, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (138, 1, 145, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (139, 1, 146, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (140, 1, 147, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (141, 1, 148, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (142, 1, 149, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (143, 1, 150, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (144, 1, 151, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (145, 1, 152, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (146, 1, 153, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (147, 1, 154, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (148, 1, 155, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (149, 1, 156, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (150, 1, 157, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (151, 1, 158, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (152, 1, 159, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (153, 1, 160, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (154, 1, 161, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (155, 1, 162, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (156, 1, 163, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (157, 1, 164, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (158, 1, 165, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (159, 1, 166, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (160, 1, 167, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (161, 1, 168, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (162, 1, 169, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (163, 1, 170, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (164, 1, 171, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (165, 1, 172, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (166, 1, 173, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (167, 1, 174, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (168, 1, 175, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (169, 1, 176, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (170, 1, 177, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (171, 1, 178, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (172, 1, 179, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (173, 1, 180, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (174, 1, 181, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (175, 1, 182, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (176, 1, 183, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (177, 1, 184, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (178, 1, 185, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (179, 1, 186, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (180, 1, 187, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (181, 1, 188, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (182, 1, 189, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (183, 1, 190, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (184, 1, 191, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (185, 1, 192, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (186, 1, 193, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (187, 1, 194, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (188, 1, 195, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (189, 1, 196, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (190, 1, 197, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (191, 1, 198, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (192, 1, 199, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (193, 1, 200, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (194, 1, 201, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (195, 1, 202, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (196, 1, 203, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (197, 1, 204, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (198, 1, 205, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (199, 1, 206, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (200, 1, 207, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (201, 1, 208, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (202, 1, 209, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (203, 1, 210, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (204, 1, 211, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (205, 1, 212, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (206, 1, 213, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (207, 1, 214, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (208, 1, 215, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (209, 1, 216, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (210, 1, 217, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (211, 1, 218, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (212, 1, 219, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (213, 1, 220, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (214, 1, 221, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (215, 1, 222, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (216, 1, 223, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (217, 1, 224, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (218, 1, 225, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (219, 1, 226, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (220, 1, 227, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (221, 1, 228, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (222, 1, 229, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (223, 1, 230, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (224, 1, 231, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (225, 1, 232, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (226, 1, 233, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (227, 1, 234, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (228, 1, 235, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (229, 1, 236, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (230, 1, 237, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (231, 1, 238, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (232, 1, 239, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (233, 1, 240, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (234, 1, 241, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (235, 1, 242, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (236, 1, 243, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (237, 1, 244, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (238, 1, 245, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (239, 1, 246, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (240, 1, 247, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (241, 1, 248, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (242, 1, 249, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (243, 1, 250, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (244, 1, 251, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (245, 1, 252, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (246, 1, 253, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (247, 1, 254, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (248, 1, 255, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (249, 1, 256, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (250, 1, 257, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (251, 1, 258, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (252, 1, 259, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (253, 1, 260, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (254, 1, 261, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (255, 1, 262, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (256, 1, 263, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (257, 1, 264, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (258, 1, 265, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (259, 1, 266, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (260, 1, 267, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (261, 1, 268, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (262, 1, 269, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (263, 1, 270, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (264, 1, 271, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (265, 1, 272, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (266, 1, 273, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (267, 1, 274, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (268, 1, 275, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (269, 1, 276, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (270, 1, 277, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (271, 1, 278, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (272, 1, 279, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (273, 1, 280, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (274, 1, 281, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (275, 1, 282, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (276, 1, 283, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (277, 1, 284, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (278, 1, 285, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (279, 1, 286, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (280, 1, 287, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (281, 1, 288, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (282, 1, 289, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (283, 1, 290, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (284, 1, 291, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (285, 1, 292, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (286, 1, 293, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (287, 1, 294, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (288, 1, 295, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (289, 1, 296, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (290, 1, 297, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (291, 1, 298, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (292, 1, 299, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (293, 1, 300, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (294, 1, 301, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (295, 1, 302, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (296, 1, 303, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (297, 1, 304, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (298, 1, 305, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (299, 1, 306, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (300, 1, 307, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (301, 1, 308, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (302, 1, 309, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (303, 1, 310, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (304, 1, 311, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (305, 1, 312, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (306, 1, 313, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (307, 1, 314, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (308, 1, 315, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (309, 1, 316, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (310, 1, 317, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (311, 1, 318, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (312, 1, 319, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (313, 1, 320, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (314, 1, 321, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (315, 1, 322, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (316, 1, 323, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (317, 1, 324, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (318, 1, 325, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (319, 1, 326, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (320, 1, 327, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (321, 1, 328, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (322, 1, 329, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (323, 1, 330, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (324, 1, 331, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (325, 1, 332, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (326, 1, 333, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (327, 1, 334, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (328, 1, 335, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (329, 1, 336, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (330, 1, 337, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (331, 1, 338, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (332, 1, 339, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (333, 1, 340, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (334, 1, 341, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (335, 1, 342, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (336, 1, 343, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (337, 1, 344, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (338, 1, 345, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (339, 1, 346, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (340, 1, 347, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (341, 1, 348, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (342, 1, 349, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (343, 1, 350, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (344, 1, 351, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (345, 1, 352, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (346, 1, 353, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (347, 1, 354, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (348, 1, 355, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (349, 1, 356, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (350, 1, 357, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (351, 1, 358, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (352, 1, 359, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (353, 1, 360, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (354, 1, 361, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (355, 1, 362, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (356, 1, 363, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (357, 1, 364, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (358, 1, 365, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (359, 1, 366, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (360, 1, 367, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (361, 1, 368, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (362, 1, 369, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (363, 1, 370, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (364, 1, 371, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (365, 1, 372, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (366, 1, 373, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (367, 1, 374, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (368, 1, 375, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (369, 1, 376, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (370, 1, 377, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (371, 1, 378, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (372, 1, 379, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (373, 1, 380, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (374, 1, 381, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (375, 1, 382, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (376, 1, 383, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (377, 1, 384, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (378, 1, 385, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (379, 1, 386, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (380, 1, 387, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (381, 1, 388, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (382, 1, 389, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (383, 1, 390, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (384, 1, 391, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (385, 1, 392, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (386, 1, 393, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (387, 1, 394, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (388, 1, 395, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (389, 1, 396, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (390, 1, 397, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (391, 1, 398, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (392, 1, 399, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (393, 1, 400, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (394, 1, 401, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (395, 1, 402, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (396, 1, 403, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (397, 1, 404, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (398, 1, 405, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (399, 1, 406, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (400, 1, 407, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (401, 1, 408, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (402, 1, 409, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (403, 1, 410, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (404, 1, 411, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (405, 1, 412, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (406, 1, 413, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (407, 1, 414, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (408, 1, 415, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (409, 1, 416, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (410, 1, 417, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (411, 1, 418, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (412, 1, 419, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (413, 1, 420, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (414, 1, 421, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (415, 1, 422, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (416, 1, 423, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (417, 1, 424, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (418, 1, 425, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (419, 1, 426, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (420, 1, 427, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (421, 1, 428, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (422, 1, 429, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (423, 1, 430, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (424, 1, 431, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (425, 1, 432, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (426, 1, 433, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (427, 1, 434, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (428, 1, 435, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (429, 1, 436, 'BOS', NULL, NULL);
INSERT INTO public.act_session_hall VALUES (430, 1, 437, 'BOS', NULL, NULL);


--
-- TOC entry 3423 (class 0 OID 210507)
-- Dependencies: 213
-- Data for Name: act_session_info; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.act_session_info VALUES (1, 1, 'Polifonik Koro Konseri', '2024-03-07', 0);


--
-- TOC entry 3425 (class 0 OID 210517)
-- Dependencies: 215
-- Data for Name: act_ticket; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3436 (class 0 OID 210980)
-- Dependencies: 226
-- Data for Name: installment; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3434 (class 0 OID 210973)
-- Dependencies: 224
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3440 (class 0 OID 211042)
-- Dependencies: 230
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3430 (class 0 OID 210959)
-- Dependencies: 220
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3438 (class 0 OID 211016)
-- Dependencies: 228
-- Data for Name: student_term; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3437 (class 0 OID 211001)
-- Dependencies: 227
-- Data for Name: student_user; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3432 (class 0 OID 210966)
-- Dependencies: 222
-- Data for Name: term; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3428 (class 0 OID 210950)
-- Dependencies: 218
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 211
-- Name: act_category_idcolumn1_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.act_category_idcolumn1_seq', 6, true);


--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 209
-- Name: act_hall_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.act_hall_id_seq', 437, true);


--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 225
-- Name: installment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.installment_id_seq', 1, false);


--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 223
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.payment_id_seq', 1, false);


--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 229
-- Name: settings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.settings_id_seq', 1, false);


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 219
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.student_id_seq', 1, false);


--
-- TOC entry 3459 (class 0 OID 0)
-- Dependencies: 221
-- Name: term_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.term_id_seq', 1, false);


--
-- TOC entry 3460 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


--
-- TOC entry 3236 (class 2606 OID 210504)
-- Name: act_category act_category_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_category
    ADD CONSTRAINT act_category_pk PRIMARY KEY (id);


--
-- TOC entry 3240 (class 2606 OID 210514)
-- Name: act_hall act_hall_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_hall
    ADD CONSTRAINT act_hall_pk PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 210516)
-- Name: act_seat act_seat_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_seat
    ADD CONSTRAINT act_seat_pk PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 210532)
-- Name: act_session_hall act_session_hall_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_session_hall
    ADD CONSTRAINT act_session_hall_pk PRIMARY KEY (id);


--
-- TOC entry 3238 (class 2606 OID 210525)
-- Name: act_session_info act_session_info_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_session_info
    ADD CONSTRAINT act_session_info_pk PRIMARY KEY (id);


--
-- TOC entry 3242 (class 2606 OID 210527)
-- Name: act_ticket act_ticket_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_ticket
    ADD CONSTRAINT act_ticket_pk PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 211020)
-- Name: student_term enrollments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_term
    ADD CONSTRAINT enrollments_pkey PRIMARY KEY (student_id, term_id);


--
-- TOC entry 3256 (class 2606 OID 210985)
-- Name: installment installment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.installment
    ADD CONSTRAINT installment_pkey PRIMARY KEY (id);


--
-- TOC entry 3254 (class 2606 OID 210978)
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- TOC entry 3262 (class 2606 OID 211049)
-- Name: settings settings_key_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_key_key UNIQUE (key);


--
-- TOC entry 3264 (class 2606 OID 211047)
-- Name: settings settings_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (id);


--
-- TOC entry 3250 (class 2606 OID 210964)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- TOC entry 3258 (class 2606 OID 211005)
-- Name: student_user student_relationships_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_user
    ADD CONSTRAINT student_relationships_pkey PRIMARY KEY (user_id, student_id);


--
-- TOC entry 3252 (class 2606 OID 210971)
-- Name: term term_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.term
    ADD CONSTRAINT term_pkey PRIMARY KEY (id);


--
-- TOC entry 3246 (class 2606 OID 210957)
-- Name: user user_mail_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_mail_key UNIQUE (mail);


--
-- TOC entry 3248 (class 2606 OID 210955)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 3265 (class 2606 OID 210564)
-- Name: act_seat act_seat_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_seat
    ADD CONSTRAINT act_seat_fk FOREIGN KEY (category_id) REFERENCES public.act_category(id);


--
-- TOC entry 3266 (class 2606 OID 210569)
-- Name: act_seat act_seat_fk2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_seat
    ADD CONSTRAINT act_seat_fk2 FOREIGN KEY (hall_id) REFERENCES public.act_hall(id);


--
-- TOC entry 3270 (class 2606 OID 210549)
-- Name: act_session_hall act_session_hall_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_session_hall
    ADD CONSTRAINT act_session_hall_fk_1 FOREIGN KEY (session_id) REFERENCES public.act_session_info(id);


--
-- TOC entry 3271 (class 2606 OID 210574)
-- Name: act_session_hall act_session_hall_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_session_hall
    ADD CONSTRAINT act_session_hall_fk_2 FOREIGN KEY (seat_id) REFERENCES public.act_seat(id);


--
-- TOC entry 3272 (class 2606 OID 211036)
-- Name: act_session_hall act_session_hall_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_session_hall
    ADD CONSTRAINT act_session_hall_user_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 3267 (class 2606 OID 210559)
-- Name: act_session_info act_session_info_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_session_info
    ADD CONSTRAINT act_session_info_fk FOREIGN KEY (hall_id) REFERENCES public.act_hall(id);


--
-- TOC entry 3268 (class 2606 OID 210533)
-- Name: act_ticket act_ticket_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_ticket
    ADD CONSTRAINT act_ticket_fk FOREIGN KEY (session_hall_id) REFERENCES public.act_session_hall(id);


--
-- TOC entry 3269 (class 2606 OID 211031)
-- Name: act_ticket act_ticket_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.act_ticket
    ADD CONSTRAINT act_ticket_user_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 3278 (class 2606 OID 211021)
-- Name: student_term enrollments_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_term
    ADD CONSTRAINT enrollments_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- TOC entry 3279 (class 2606 OID 211026)
-- Name: student_term enrollments_term_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_term
    ADD CONSTRAINT enrollments_term_id_fkey FOREIGN KEY (term_id) REFERENCES public.term(id);


--
-- TOC entry 3273 (class 2606 OID 210996)
-- Name: installment installment_payment_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.installment
    ADD CONSTRAINT installment_payment_id_fkey FOREIGN KEY (payment_id) REFERENCES public.payment(id);


--
-- TOC entry 3274 (class 2606 OID 210991)
-- Name: installment installment_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.installment
    ADD CONSTRAINT installment_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- TOC entry 3275 (class 2606 OID 210986)
-- Name: installment installment_term_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.installment
    ADD CONSTRAINT installment_term_id_fkey FOREIGN KEY (term_id) REFERENCES public.term(id);


--
-- TOC entry 3276 (class 2606 OID 211011)
-- Name: student_user student_relationships_student_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_user
    ADD CONSTRAINT student_relationships_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- TOC entry 3277 (class 2606 OID 211006)
-- Name: student_user student_relationships_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.student_user
    ADD CONSTRAINT student_relationships_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);


-- Completed on 2024-01-30 02:06:41

--
-- PostgreSQL database dump complete
--

