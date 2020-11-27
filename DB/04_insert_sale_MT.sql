conn SALESAMPLE/SALESAMPLE

insert into MT_ITEM_GENRE values('00000001','コピー用紙');
insert into MT_ITEM_GENRE values('00000002','クリアファイル');
insert into MT_ITEM_GENRE values('00000003','付箋');


insert into MT_ITEM values('00000001', 'コピー用紙A4_01',    '00000001', 'A4',          3000);
insert into MT_ITEM values('00000002', 'コピー用紙A3_01',    '00000001', 'A3',          3100);
insert into MT_ITEM values('00000003', 'コピー用紙B5_01',    '00000001', 'B5',          2900);
insert into MT_ITEM values('00000004', 'クリアファイル_010', '00000002', '10枚パック',   100);
insert into MT_ITEM values('00000005', 'クリアファイル_100', '00000002', '100枚パック', 1000);
insert into MT_ITEM values('00000006', '付箋_大_010',        '00000003', '大',           900);
insert into MT_ITEM values('00000007', '付箋_中_010',        '00000003', '中',           700);
insert into MT_ITEM values('00000008', '付箋_小_010',        '00000003', '小',           600);

insert into MT_CUSTOMER values('C0000001', '新橋商店');
insert into MT_CUSTOMER values('C0000002', '虎ノ門商事');
insert into MT_CUSTOMER values('C0000003', '霞が関産業');
insert into MT_CUSTOMER values('O0000001', 'その他');

commit;