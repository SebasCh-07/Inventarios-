-- eliminar las tablas si existen
drop table if exists detalle_pedido;
drop table if exists detalles_ventas;
drop table if exists historial_stock;
drop table if exists cabecera_ventas;
drop table if exists productos;
drop table if exists unidades_medida;
drop table if exists categorias_unidad_medida;
drop table if exists cabecera_pedido;
drop table if exists estados_pedido;
drop table if exists proveedores;
drop table if exists tipo_documento;
drop table if exists categorias;

-- crear las tablas
create table categorias (
    codigo_cat serial not null,
    nombre varchar(100) not null,
    categoria_padre int,
    constraint categorias_pk primary key (codigo_cat),
    constraint categorias_fk foreign key (categoria_padre) references categorias(codigo_cat)
);

create table tipo_documento (
    codigo char(2) not null,
    descripcion char(10) not null,
    constraint tipo_documento_pk primary key (codigo)
);

create table proveedores (
    identificador char(13) not null,
    tipo_documento char(2) not null,
    nombre varchar(20) not null,
    telefono char(10) not null,
    correo char(30) not null,
    direccion varchar(15) not null,
    constraint proveedores_pk primary key (identificador),
	constraint tipo_documento_fk foreign key(tipo_documento) references tipo_documento(codigo)
);

create table estados_pedido (
    codigo char(2) not null,
    descripcion char(20) not null,
    constraint estados_pedido_pk primary key (codigo)
);

create table cabecera_pedido (
    numero serial not null,
    proveedor char(13) not null,
    fecha date not null,
    estado char(2) not null,
    constraint cabecera_pedido_pk primary key (numero),
    constraint cabecera_pedido_fk1 foreign key (proveedor) references proveedores(identificador),
    constraint cabecera_pedido_fk2 foreign key (estado) references estados_pedido(codigo)
);

create table detalle_pedido (
    codigo serial not null,
    cabecera_pedido int not null,
    producto int not null,
    cantidad int not null,
    subtotal money not null,
    cantidad_recibida int not null,
    constraint detalle_pedido_pk primary key (codigo),
    constraint detalle_pedido_fk1 foreign key (cabecera_pedido) references cabecera_pedido(numero)
);

create table categorias_unidad_medida (
    codigo_udm char(2) not null,
    nombre varchar(100) not null,
    constraint categorias_unidad_medida_pk primary key (codigo_udm)
);

create table unidades_medida (
    codigo_udm char(2) not null,
    descripcion varchar(100) not null,
    categoria_udm char(2) not null,
    constraint unidades_medida_pk primary key (codigo_udm),
    constraint unidades_medida_fk foreign key (categoria_udm) references categorias_unidad_medida(codigo_udm)
);

create table productos (
    codigo_pro serial not null,
    nombre varchar(100) not null,
    udm varchar(3) not null,
    precio_venta money not null,
    tiene_iva varchar(5) not null,
    coste money not null,
    categoria int not null,
    stock int not null,
    constraint productos_pk primary key (codigo_pro),
    constraint productos_fk1 foreign key (udm) references unidades_medida(codigo_udm),
    constraint productos_fk2 foreign key (categoria) references categorias(codigo_cat)
);

create table historial_stock (
    codigo serial not null,
    fecha timestamp not null,
    referencia varchar(10) not null,
    producto int not null,
    cantidad int not null,
    constraint historial_stock_pk primary key (codigo),
    constraint historial_stock_fk foreign key (producto) references productos(codigo_pro)
);

create table cabecera_ventas (
    codigo serial not null,
    fecha timestamp not null,
    total_iva money not null,
    iva money not null,
    total money not null,
    constraint cabecera_ventas_pk primary key (codigo)
);

create table detalles_ventas (
    codigo serial not null,
    cabecera_ventas int not null,
    producto int not null,
    cantidad int not null,
    precio_venta money not null,
    subtotal money not null,
    subtotal_iva money not null,
    constraint detalles_ventas_pk primary key (codigo),
    constraint detalles_ventas_fk1 foreign key (cabecera_ventas) references cabecera_ventas(codigo),
    constraint detalles_ventas_fk2 foreign key (producto) references productos(codigo_pro)
);

insert into categorias(nombre,categoria_padre)
values('Materia Prima',null);
insert into categorias(nombre,categoria_padre)
values('Proteina',1);
insert into categorias(nombre,categoria_padre)
values('Salsas',1);
insert into categorias(nombre,categoria_padre)
values('Punto de Venta',null);
insert into categorias(nombre,categoria_padre)
values('Bebidas',4);
insert into categorias(nombre,categoria_padre)
values('Con Alcohol',5);
insert into categorias(nombre,categoria_padre)
values('Sin Alchol',5);

insert into tipo_documento(codigo,descripcion)
values('C','cedula');
insert into tipo_documento(codigo,descripcion)
values('R','ruc');

insert into proveedores(identificador,tipo_documento,nombre,telefono,correo,direccion)
values('1753999364','C','Sebastian Chamorro','0995708722','sebasch.est@gmail.com','Cumbaya');
insert into proveedores(identificador,tipo_documento,nombre,telefono,correo,direccion)
values('1792285747001','R','Snacks SA','0989095031','snacks@gmail.com','La Tola');

insert into estados_pedido(codigo,descripcion)
values('S','solicitado');
insert into estados_pedido(codigo,descripcion)
values('R','recibido');

insert into cabecera_pedido(proveedor,fecha,estado)
values('1753999364','20/11/2023','R');
insert into cabecera_pedido(proveedor,fecha,estado)
values('1753999364','20/11/2023','R');

insert into detalle_pedido(cabecera_pedido,producto,cantidad,subtotal,cantidad_recibida)
values(1,1,100,37.29,100);
insert into detalle_pedido(cabecera_pedido,producto,cantidad,subtotal,cantidad_recibida)
values(1,4,50,11.8,20);
insert into detalle_pedido(cabecera_pedido,producto,cantidad,subtotal,cantidad_recibida)
values(2,1,10,3.73,10);

insert into Categorias_Unidad_Medida(codigo_udm,nombre)
values('U','Unidades');
insert into Categorias_Unidad_Medida(codigo_udm,nombre)
values('V','Volumen');
insert into Categorias_Unidad_Medida(codigo_udm,nombre)
values('P','Peso');

insert into Unidades_medida(codigo_udm,descripcion,categoria_udm)
values('ml','mililitros','V');
insert into Unidades_medida(codigo_udm,descripcion,categoria_udm)
values('L','litros','V');
insert into Unidades_medida(codigo_udm,descripcion,categoria_udm)
values('U','unidad','U');
insert into Unidades_medida(codigo_udm,descripcion,categoria_udm)
values('D','docena','U');
insert into Unidades_medida(codigo_udm,descripcion,categoria_udm)
values('g','gramos','P');
insert into Unidades_medida(codigo_udm,descripcion,categoria_udm)
values('Kg','Kilogramos','P');
insert into Unidades_medida(codigo_udm,descripcion,categoria_udm)
values('lb','libras','P');

insert into productos(nombre,udm,precio_venta,tiene_iva,coste,categoria,stock)
values('Coca Cola Pequeña','U',0.5804,'true',0.3729,7,110);
insert into productos(nombre,udm,precio_venta,tiene_iva,coste,categoria,stock)
values('Salsa de Tomate','Kg',0.95,'true',0.8736,3,0);
insert into productos(nombre,udm,precio_venta,tiene_iva,coste,categoria,stock)
values('Mostaza','Kg',0.95,'true',0.89,3,0);
insert into productos(nombre,udm,precio_venta,tiene_iva,coste,categoria,stock)
values('Fuze Tea','U',0.8,'true',0.7,7,50);

insert into historial_stock(fecha,referencia,producto,cantidad)
values('20/11/2023 19:59','Pedido 1',1,100);
insert into historial_stock(fecha,referencia,producto,cantidad)
values('20/11/2023 19:59','Pedido 1',4,100);
insert into historial_stock(fecha,referencia,producto,cantidad)
values('20/11/2023 20:00','Pedido 2',1,10);

insert into cabecera_ventas(fecha,total_iva,iva,total)
values('20/11/2023 20:00',3.26,0.39,3.65);

insert into detalles_ventas(cabecera_ventas,producto,cantidad,precio_venta,subtotal,subtotal_iva)
values(1,1,5,0.58,2.9,3.25);
insert into detalles_ventas(cabecera_ventas,producto,cantidad,precio_venta,subtotal,subtotal_iva)
values(1,4,1,0.36,0.36,0.4);

select * from tipo_documento;
select * from proveedores;
select * from estados_pedido;
select * from cabecera_pedido;
select * from detalle_pedido;
select * from Categorias_Unidad_Medida;
select * from Unidades_medida;
select * from categorias;
select * from productos;
select * from historial_stock;
select * from cabecera_ventas;
select * from detalles_ventas;
