conn SALESAMPLE/SALESAMPLE

insert into TR_SALES_OUTLINE values(TR_SALES_OUTLINE_SALES_ID_SEQ.NEXTVAL,'C0000001','u003','2020-12-01');
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'CP000001',1,3000);
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'CF000001',2,100);

insert into TR_SALES_OUTLINE values(TR_SALES_OUTLINE_SALES_ID_SEQ.NEXTVAL,'C0000002','u003','2020-12-02');
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'CP000002',1,3100);
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'CF000001',2,100);


insert into TR_SALES_OUTLINE values(TR_SALES_OUTLINE_SALES_ID_SEQ.NEXTVAL,'C0000002','u004','2020-12-01');
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'SN000001',1,700);
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'SN000002',10,500);

insert into TR_SALES_OUTLINE values(TR_SALES_OUTLINE_SALES_ID_SEQ.NEXTVAL,'C0000003','u004','2020-12-03');
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'CP000002',2,3100);
insert into TR_SALES_DETAIL values(TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL,TR_SALES_OUTLINE_SALES_ID_SEQ.CURRVAL,'SN000003',10,300);

commit;