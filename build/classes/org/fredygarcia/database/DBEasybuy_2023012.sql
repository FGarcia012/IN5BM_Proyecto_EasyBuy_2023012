set global time_zone = '-6:00';
drop database if exists DBEasybuy_2023012;
create database DBEasybuy_2023012;
use DBEasybuy_2023012;

create table Clientes (
	IDClientes int,
    nombreClientes varchar (50),
    apellidosClientes varchar (50),
	NITClientes varchar (10),
	telefonoClientes varchar (45),
	direccionClientes varchar(150),
	correoClientes varchar (45),
	primary key PK_Clientes(IDClientes)
	);

create table CargoEmpleado (
	IDCargoEmpleado int ,
    nombreCargo varchar (45),
    descripcionCargo varchar (45),
    primary key PK_CargoEmpleado(IDCargoEmpleado)
);

create table TipoProducto(
	IDTipoProducto int ,
    descripcion varchar (45),
    primary key PK_TipoProducto(IDTipoProducto)
);

create table Compras(
    numDocumento int ,
    fechaDocumento date,
    descripcion varchar(60),
    totalDocumento decimal(20,2),
    primary key PK_Compras(numDocumento)
);

create table Proveedores(
	IDProveedor int,
    nombresProveedor varchar(50),
    apellidosProveedor varchar (50),
    NITProveedor varchar(10),
    telefonoProveedor varchar (45),
    direccionProveedor varchar (150),
    correoProveedor varchar(45),
    razonSocial varchar (45),
    contactoPrincipal varchar (100),
    paginaWeb varchar(45),
    primary key PK_Proveedores(IDProveedor)
);

create table Productos(
	IDProductos varchar(15),
    descProducto varchar(45),
    precioUnitario decimal(10,2),
    precioDocena decimal(10,2),
    precioMayor decimal(10,2),
    imagenProducto varchar(45),
    existencia int,
    IDTipoProducto int,
    IDProveedor int,
    primary key PK_Productos(IDProductos),
    foreign key (IDTipoProducto) references TipoProducto(IDTipoProducto),
    foreign key (IDProveedor) references Proveedores(IDProveedor)
);

delimiter $$

create procedure sp_AgregarClientes(in IDClientes int,in nombreClientes varchar(50), in apellidosClientes varchar(50), in NITClientes varchar(10),in telefonoClientes varchar(45), in direccionClientes varchar(150), in correoClientes varchar(45)
)
begin
    insert into Clientes (IDClientes, nombreClientes, apellidosClientes, NITClientes, telefonoClientes, direccionClientes, correoClientes)
    values (IDClientes, nombreClientes, apellidosClientes, NITClientes, telefonoClientes, direccionClientes, correoClientes);
end$$

delimiter ;

call sp_AgregarClientes(1,'Daniel', 'sacol', '78542585', '59866852', 'San jose Pinula', 'danieledusc@gmail.com');
call sp_AgregarClientes(2,'Fredy', 'Garcia', '1254789658', '52365478', 'San Julian', 'fgarcia-2023012@kinal.edu.gt');

delimiter $$
create procedure sp_ListarClientes()
begin
    select IDClientes, nombreClientes, apellidosClientes, NITClientes, telefonoClientes, direccionClientes, correoClientes from Clientes;
end$$
delimiter ;

call sp_ListarClientes();

delimiter $$

create procedure sp_BuscarClientes(in _IDClientes int)
begin
    select * from Clientes where IDClientes = _IDClientes;
end$$

delimiter ;

call sp_BuscarClientes(2);

delimiter $$

create procedure sp_ActualizarClientes(
    in _IDClientes int, in nombreClientes varchar(50), in apellidosClientes varchar(50),
    in NITClientes varchar(10), in telefonoClientes varchar(150), in direccionClientes varchar(150),
    in correoClientes varchar(45))
begin
    update Clientes
    set
        NITClientes = NITClientes,
        nombreClientes = nombreClientes,
        apellidosClientes = apellidosClientes,
        direccionClientes = direccionClientes,
        telefonoClientes = telefonoClientes,
        correoClientes = correoClientes
    where
        IDClientes = _IDClientes;
end$$

delimiter ;

call sp_ActualizarClientes(1,'Fredy', 'Garcia', '78542585', '59866852', 'San jose Pinula', 'danieledusc@gmail.com');

delimiter $$

create procedure sp_EliminarClientes(in _IDClientes int)
begin
    delete from Clientes where IDClientes = _IDClientes;
end$$

delimiter ;

call sp_EliminarClientes(1);

-- Cargo Empleado
delimiter $$

create procedure sp_AgregarCargoEmpleado(
    in IDCargoEmpleado int, in nombreCargo varchar(45), in descripcionCargo varchar(45)
)
begin
    insert into CargoEmpleado (IDCargoEmpleado,nombreCargo, descripcionCargo)
    values (IDCargoEmpleado,nombreCargo, descripcionCargo);
end$$

delimiter ;

call sp_AgregarCargoEmpleado(1,'Chalan', 'Es el constructor');
call sp_AgregarCargoEmpleado(2, 'Maestro de obras','Sera el supervisor');

delimiter $$
create procedure sp_ListarCargoEmpleado()
begin
    select IDCargoEmpleado, nombreCargo,descripcionCargo from CargoEmpleado;
end$$

delimiter ;

call sp_ListarCargoEmpleado();

delimiter $$
create procedure sp_BuscarCargoEmpleado(in _IDCargoEmpleado int)
begin
    select * from CargoEmpleado where IDCargoEmpleado = _IDCargoEmpleado;
end$$

delimiter ;

call sp_BuscarCargoEmpleado(2);

delimiter $$
create procedure sp_ActualizarCargoEmpleado(
    in _IDCargoEmpleado int, in nombreCargo varchar(45), in descripcionCargo varchar(45)
)
begin
    update CargoEmpleado
    set
        nombreCargo = nombreCargo,
        descripcionCargo = descripcionCargo
    where
        IDCargoEmpleado = _IDCargoEmpleado;
end$$

delimiter ;

call sp_ActualizarCargoEmpleado(2,'Arquitecto','Es el superior');

delimiter $$
create procedure sp_EliminarCargoEmpleado(in _IDCargoEmpleado int)
begin
    delete from CargoEmpleado where IDCargoEmpleado = _IDCargoEmpleado;
end$$

delimiter ;

call sp_EliminarCargoEmpleado(1);

-- Tipo de producto
delimiter $$

create procedure sp_AgregarTipoProducto(
    in IDTipoProducto int, in descripcion varchar(45)
)
begin
    insert into TipoProducto (IDTipoProducto,descripcion)
    values (IDTipoProducto,descripcion);
end$$

delimiter ;

call sp_AgregarTipoProducto(1,'Manzanas verdes sin semillas provienen de E.U');
call sp_AgregarTipoProducto(2,'Uvas vedes sin semillas provienen de China');

delimiter $$

create procedure sp_ListarTipoProducto()
begin
    select IDTipoProducto, descripcion from TipoProducto;
end$$

delimiter ;

call sp_ListarTipoProducto();

delimiter $$
create procedure sp_BuscarTipoProducto(in _IDTipoProducto int)
begin
    select * from TipoProducto where IDTipoProducto = _IDTipoProducto;
end$$

delimiter ;

call sp_BuscarTipoProducto(2);

delimiter $$
create procedure sp_ActualizarTipoProducto(
    in _IDTipoProducto int, in descripcion varchar(45)
)
begin
    update TipoProducto
    set
        descripcion = descripcion
    where
        IDTipoProducto = _IDTipoProducto;
end$$

delimiter ;

call sp_ActualizarTipoProducto(2,'Cerezas provenientes de europa');

delimiter $$
create procedure sp_EliminarTipoProducto(in _IDTipoProducto int)
begin
    delete from TipoProducto where IDTipoProducto = _IDTipoProducto;
end$$

delimiter ;

call sp_EliminarTipoProducto(1);

-- Compras
delimiter $$

create procedure sp_AgregarCompra(
	in numDocumento int,
    in fechaDocumento date,
    in descripcion varchar(60),
    in totalDocumento decimal(20,2)
)
begin
    insert into Compras (numDocumento,fechaDocumento, descripcion, totalDocumento)
    values (numDocumento,fechaDocumento, descripcion, totalDocumento);
end$$

delimiter ;

call sp_AgregarCompra(1, '2023-05-09', 'Compra de suministros', 500.00);
call sp_AgregarCompra(2, '2024-05-09', 'Compra de suministros', 1000.00);

delimiter $$
create procedure sp_ListarCompras()
begin
    select numDocumento,fechaDocumento,descripcion,totalDocumento from Compras;
end$$

delimiter ;

call sp_ListarCompras();

delimiter $$
create procedure sp_BuscarCompra(in _numDocumento int)
begin
    select * from Compras where numDocumento = _numDocumento;
end$$

delimiter ;

call sp_BuscarCompra(2);

delimiter $$

create procedure sp_ActualizarCompra(
    in _numDocumento int,
    in fechaDocumento date,
    in descripcion varchar(60),
    in totalDocumento decimal(20,2)
)
begin
    update Compras
    set
        fechaDocumento = fechaDocumento,
        descripcion = descripcion,
        totalDocumento = totalDocumento
    where
        numDocumento = _numDocumento;
end$$

delimiter ;

call sp_ActualizarCompra(2, '3075-05-09', 'Compra de comida', 750.00);

delimiter $$

create procedure sp_EliminarCompra(in _numDocumento int)
begin
    delete from Compras where numDocumento = _numDocumento;
end$$

delimiter ;

call sp_EliminarCompra(1);

-- Proveedores
delimiter $$

create procedure sp_AgregarProveedor (
    in IDProveedor int,
    in nombresProveedor varchar(50),
    in apellidosProveedor varchar(50),
    in NITProveedor varchar(10),
    in telefonoProveedor varchar(45),
    in direccionProveedor varchar(150),
    in correoProveedor varchar(45),
    in razonSocial varchar(45),
    in contactoPrincipal varchar(100),
    in paginaWeb varchar(45))
begin
    insert into Proveedores (IDProveedor, nombresProveedor, apellidosProveedor, NITProveedor, telefonoProveedor, direccionProveedor, correoProveedor, razonSocial, contactoPrincipal, paginaWeb)
    values (IDProveedor, nombresProveedor, apellidosProveedor, NITProveedor, telefonoProveedor, direccionProveedor, correoProveedor, razonSocial, contactoPrincipal, paginaWeb);
end $$

delimiter ;

call sp_AgregarProveedor(1, 'Gasolinera Express', 'S.A.', '1458723659', '1234567890', 'Av. Principal 123, Zona 1','info@gasolineraexpress.com' , 'Gasolinera Expres S.A','Juan Pérez', 'www.gasolineraexpress.com');
call sp_AgregarProveedor(2, 'Distribuidora de Alimentos', 'Dialiment S.A.', '3986597854', '2345678901', 'Av. Comercial 456, Zona 2', 'info@dialiment.com', 'Dialiment S.A.','María Gómez' , 'www.dialiment.com');

delimiter $$

create procedure sp_ListarProveedores()
begin
    select
        IDProveedor,
        nombresProveedor,
        apellidosProveedor,
        NITProveedor,
        telefonoProveedor,
        direccionProveedor,
        correoProveedor,
        razonSocial,
        contactoPrincipal,
        paginaWeb
    from
        Proveedores;
end $$

delimiter ;

call sp_ListarProveedores();

delimiter $$

create procedure sp_BuscarProveedor (in _IDProveedor int)
begin
    select
        IDProveedor,
        nombresProveedor,
        apellidosProveedor,
        NITProveedor,
        telefonoProveedor,
        direccionProveedor,
        correoProveedor,
        razonSocial,
        contactoPrincipal,
        paginaWeb
    from
        Proveedores
    where
        IDProveedor = _IDProveedor;
end $$

delimiter ;

call sp_BuscarProveedor(1);

delimiter $$

create procedure sp_EliminarProveedor (in _IDProveedor int)
begin
    delete from Proveedores
    where IDProveedor = _IDProveedor;
end $$

delimiter ;

call sp_EliminarProveedor(1);

delimiter $$

create procedure sp_ActualizarProveedores (
    in _IDProveedor int,
    in nombresProveedor varchar(50),
    in apellidosProveedor varchar(50),
    in NITProveedor varchar(10),
    in telefonoProveedor varchar(45),
    in direccionProveedor varchar(150),
    in correoProveedor varchar(45),
    in razonSocial varchar(45),
    in contactoPrincipal varchar(100),
    in paginaWeb varchar(45))
begin
    update Proveedores
    set
        nombresProveedor = nombresProveedor,
        apellidosProveedor = apellidosProveedor,
        NITProveedor = NITProveedor,
        telefonoProveedor = telefonoProveedor,
        direccionProveedor = direccionProveedor,
        correoProveedor = correoProveedor,
        razonSocial = razonSocial,
        contactoPrincipal = contactoPrincipal,
        paginaWeb = paginaWeb
    where
        IDProveedor = _IDProveedor;
end $$

delimiter ;

 call sp_ActualizarProveedores(2, 'Juan', 'Perez','2145896874', '25698569', 'Calle 123','proveedor@correo.com' , 'Razón Social','Contacto' , 'www.proveedor.com');
 
 -- Productos
 
 delimiter $$

create procedure sp_AgregarProductos(in IDProductos varchar(15),in descProducto varchar(45), in precioUnitario decimal(10,2), in precioDocena decimal(10,2),in precioMayor decimal(10,2), in imagenProducto varchar(45), in existencia int, in IDTipoProducto int, in IDProveedor int
)
begin
    insert into Productos (IDProductos, descProducto, precioUnitario, precioDocena, precioMayor, imagenProducto, existencia,IDTipoProducto,IDProveedor)
    values (IDProductos, descProducto, precioUnitario, precioDocena, precioMayor, imagenProducto, existencia,IDTipoProducto,IDProveedor);
end$$

delimiter ;

call sp_AgregarProductos('P002', 'Uvas verdes sin semillas', 3.00, 30.00, 60.00, 'uvas_verdes.jpg', 150, 2, 2);
call sp_AgregarProductos('P001', 'Manzanas verdes sin semillas', 2.50, 25.00, 50.00, 'manzanas_verdes.jpg', 100, 2, 2);

delimiter $$
create procedure sp_ListarProductos()
begin
    select IDProductos, descProducto, precioUnitario, precioDocena, precioMayor, imagenProducto, existencia, IDTipoProducto, IDProveedor from Productos;
end$$
delimiter ;

call sp_ListarProductos();

delimiter $$

create procedure sp_BuscarProductos(in _IDProductos varchar(15))
begin
    select * from Productos where IDProductos = _IDProductos;
end$$

delimiter ;

call sp_BuscarProductos('P002');

delimiter $$

create procedure sp_ActualizarProductos(
    in _IDProductos varchar(15), in descProducto varchar(45), in precioUnitario decimal(10,2),
    in precioDocena decimal(10,2), in precioMayor decimal(10,2), in imagenProducto varchar(45),
    in existencia int, in IDTipoProducto int, in IDProveedor int)
begin
    update Productos
    set
        descProducto = descProducto,
        precioUnitario = precioUnitario,
        precioDocena = precioDocena,
        precioMayor = precioMayor,
        imagenProducto = imagenProducto,
        existencia = existencia,
        IDTipoProducto = IDTipoProducto,
        IDProveedor = IDProveedor
    where
        IDProductos = _IDProductos;
end$$

delimiter ;

call sp_ActualizarProductos('P002', 'Uvas verdes sin semillas', 3.00, 30.00, 60.00, 'uvas_verdes.jpg', 150, 2, 2);

delimiter $$

create procedure sp_EliminarProductos(in _IDProductos varchar(15))
begin
    delete from Productos where IDProductos = _IDProductos;
end$$

delimiter ;

call sp_EliminarProductos('P002');