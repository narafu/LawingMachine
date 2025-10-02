-- =================================================================
--  LawingMachine 데이터베이스 스키마(DDL) 스크립트 (PostgreSQL 용)
-- =================================================================

-- 1. 기존 테이블 삭제
DROP TABLE IF EXISTS brd_cmnt_info CASCADE;
DROP TABLE IF EXISTS brd_likes CASCADE;
DROP TABLE IF EXISTS brd_mstr_info CASCADE;
DROP TABLE IF EXISTS brd_views CASCADE;
DROP TABLE IF EXISTS cmmn_dtl_cd CASCADE;
DROP TABLE IF EXISTS quiz_dtl_info CASCADE;
DROP TABLE IF EXISTS quiz_mstr_info CASCADE;
DROP TABLE IF EXISTS quiz_result_info CASCADE;
DROP TABLE IF EXISTS quiz_user_ans CASCADE;
DROP TABLE IF EXISTS quiz_user_ans_dtl CASCADE;
DROP TABLE IF EXISTS user_info CASCADE;

-- 2. 기존 시퀀스 삭제
DROP SEQUENCE IF EXISTS brd_cmnt_info_seq;
DROP SEQUENCE IF EXISTS brd_likes_seq;
DROP SEQUENCE IF EXISTS brd_mstr_info_seq;
DROP SEQUENCE IF EXISTS brd_views_seq;
DROP SEQUENCE IF EXISTS cmmn_dtl_cd_seq;
DROP SEQUENCE IF EXISTS quiz_dtl_info_seq;
DROP SEQUENCE IF EXISTS quiz_mstr_info_seq;
DROP SEQUENCE IF EXISTS quiz_result_info_seq;
DROP SEQUENCE IF EXISTS quiz_user_ans_dtl_seq;
DROP SEQUENCE IF EXISTS quiz_user_ans_seq;
DROP SEQUENCE IF EXISTS user_info_seq;

-- 3. 시퀀스 생성
CREATE SEQUENCE brd_cmnt_info_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE brd_likes_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE brd_mstr_info_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE brd_views_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE cmmn_dtl_cd_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE quiz_dtl_info_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE quiz_mstr_info_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE quiz_result_info_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE quiz_user_ans_dtl_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE quiz_user_ans_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE user_info_seq START WITH 1 INCREMENT BY 50;

-- 4. 테이블 생성
CREATE TABLE brd_cmnt_info (
    brd_cmnt_info_seq integer not null,
    brd_mstr_info_seq integer,
    del_yn char(1),
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    primary key (brd_cmnt_info_seq)
);

CREATE TABLE brd_likes (
    brd_likes_seq integer not null,
    brd_mstr_info_seq integer,
    del_yn char(1),
    like_yn char(1),
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    primary key (brd_likes_seq)
);

CREATE TABLE brd_mstr_info (
    brd_mstr_info_seq integer not null,
    del_yn char(1),
    notice_yn char(1),
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    brd_type_cd varchar(10),
    subject_type_cd varchar(10),
    content varchar(255),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    title varchar(255),
    primary key (brd_mstr_info_seq)
);

CREATE TABLE brd_views (
    brd_mstr_info_seq integer,
    brd_views_seq integer not null,
    del_yn char(1),
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    primary key (brd_views_seq)
);

CREATE TABLE cmmn_dtl_cd (
    cmmn_dtl_cd_seq integer not null,
    del_yn char(1),
    srt_no integer not null,
    use_yn char(1),
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    grp_cd varchar(10),
    grp_dtl_cd varchar(10),
    grp_dtl_nm varchar(255),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    primary key (cmmn_dtl_cd_seq)
);

CREATE TABLE quiz_dtl_info (
    del_yn char(1),
    quiz_dtl_info_seq integer not null,
    quiz_mstr_info_seq integer,
    srt_no integer not null,
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    cmntr varchar(255),
    content varchar(255),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    primary key (quiz_dtl_info_seq)
);

CREATE TABLE quiz_mstr_info (
    del_yn char(1),
    exam_no integer not null,
    exam_year integer,
    quiz_mstr_info_seq integer not null,
    srt_no integer not null,
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    exam_grp_cd varchar(10),
    subject_type_cd varchar(10),
    answer varchar(255),
    cmntr varchar(255),
    content varchar(255),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    primary key (quiz_mstr_info_seq)
);

CREATE TABLE quiz_result_info (
    del_yn char(1),
    exam_no integer not null,
    exam_year integer,
    quiz_result_info_seq integer not null,
    result_cnt integer not null,
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    exam_grp_cd varchar(10),
    subject_type_cd varchar(10),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    user_id varchar(255),
    primary key (quiz_result_info_seq)
);

CREATE TABLE quiz_user_ans (
    answer_chk char(1),
    del_yn char(1),
    quiz_mstr_info_seq integer,
    quiz_user_ans_seq integer not null,
    take_rev integer not null,
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    user_answer varchar(255),
    user_chk_cnfsd varchar(255),
    user_chk_imprt varchar(255),
    user_id varchar(255),
    primary key (quiz_user_ans_seq)
);

CREATE TABLE quiz_user_ans_dtl (
    del_yn char(1),
    erase_yn char(1),
    quiz_dtl_info_seq integer,
    quiz_user_ans_dtl_seq integer not null,
    quiz_user_ans_seq integer,
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    primary key (quiz_user_ans_dtl_seq)
);

CREATE TABLE user_info (
    del_yn char(1),
    login_cnt integer not null,
    user_info_seq integer not null,
    mdfy_ts timestamp(6),
    reg_ts timestamp(6),
    email varchar(255),
    exam_ticket varchar(255),
    exam_ticket_no varchar(255),
    exam_ticket_path varchar(255),
    mdfy_id varchar(255),
    mdfy_nm varchar(255),
    membership_cd varchar(255),
    mobile varchar(255),
    nickname varchar(255),
    password varchar(255),
    regist_id varchar(255),
    regist_nm varchar(255),
    role varchar(255),
    user_id varchar(255),
    user_nm varchar(255),
    primary key (user_info_seq)
);
