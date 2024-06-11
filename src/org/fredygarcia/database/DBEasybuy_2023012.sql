SET GLOBAL time_zone = '-6:00';
DROP DATABASE IF EXISTS DBEasybuy_2023012;
CREATE DATABASE DBEasybuy_2023012;
USE DBEasybuy_2023012;

create table Clientes (
    IDClientes int,
    nombreClientes varchar(50),
    apellidosClientes varchar(50),
    NITClientes varchar(10),
    telefonoClientes varchar(45),
    direccionClientes varchar(150),
    correoClientes varchar(45),
    PRIMARY KEY (IDClientes)
);

create table CargoEmpleado (
    IDCargoEmpleado int,
    nombreCargo varchar(45),
    descripcionCargo varchar(45),
    PRIMARY KEY (IDCargoEmpleado)
);

create table TipoProducto (
    IDTipoProducto int,
    descripcion varchar(45),
    PRIMARY KEY (IDTipoProducto)
);

create table Compras (
    numDocumento int,
    fechaDocumento date,
    descripcion varchar(60),
    totalDocumento decimal(20,2),
    PRIMARY KEY (numDocumento)
);

create table Proveedores (
    IDProveedor int,
    nombresProveedor varchar(50),
    apellidosProveedor varchar(50),
    NITProveedor varchar(10),
    telefonoProveedor varchar(45),
    direccionProveedor varchar(150),
    correoProveedor varchar(45),
    razonSocial varchar(45),
    contactoPrincipal varchar(100),
    paginaWeb varchar(45),
    PRIMARY KEY (IDProveedor)
);

create table Productos (
    IDProductos varchar(15),
    descProducto varchar(45),
    precioUnitario decimal(10,2),
    precioDocena decimal(10,2),
    precioMayor decimal(10,2),
    imagenProducto varchar(45),
    existencia int,
    IDTipoProducto int,
    IDProveedor int,
    PRIMARY KEY (IDProductos),
    FOREIGN KEY (IDTipoProducto) REFERENCES TipoProducto(IDTipoProducto),
    FOREIGN KEY (IDProveedor) REFERENCES Proveedores(IDProveedor)
);

create table Empleados (
    IDEmpleados int,
    nombresEmpleado varchar(45),
    apellidosEmpleado varchar(45),
    sueldo decimal(10,2),
    direccion varchar(150),
    turno varchar(15),
    IDCargoEmpleado int,
    PRIMARY KEY (IDEmpleados),
    FOREIGN KEY (IDCargoEmpleado) REFERENCES CargoEmpleado(IDCargoEmpleado)
);

create table Factura (
    numFactura int,
    estado varchar(45),
    totalFactura decimal(10,2),
    fechaFactura varchar(45),
    IDClientes int,
    IDEmpleados int,
    PRIMARY KEY (numFactura),
    FOREIGN KEY (IDClientes) REFERENCES Clientes(IDClientes),
    FOREIGN KEY (IDEmpleados) REFERENCES Empleados(IDEmpleados)
);

create table DetalleFactura (
    IDDetalleFactura int,
    precioUnitario decimal(10,2),
    cantidad int,
    numFactura int,
    IDProductos varchar(15),
    PRIMARY KEY (IDDetalleFactura),
    FOREIGN KEY (numFactura) REFERENCES Factura(numFactura),
    FOREIGN KEY (IDProductos) REFERENCES Productos(IDProductos)
);

create table DetalleCompra (
    IDDetalleCompra int,
    costoUnitario decimal(10,2),
    cantidad int,
    IDProductos varchar(15),
    numDocumento int,
    PRIMARY KEY (IDDetalleCompra),
    FOREIGN KEY (IDProductos) REFERENCES Productos(IDProductos),
    FOREIGN KEY (numDocumento) REFERENCES Compras(numDocumento)
);

create table TelefonoProveedor (
    IDTelefonoProveedor int,
    numeroPrincipal varchar(8),
    numeroSecundario varchar(8),
    observaciones varchar(45),
    IDProveedor int,
    PRIMARY KEY (IDTelefonoProveedor),
    FOREIGN KEY (IDProveedor) REFERENCES Proveedores(IDProveedor)
);

create table EmailProveedor (
    IDEmailProveedor int,
    emailProveedor varchar(45),
    descripcion varchar(100),
    IDProveedor int,
    PRIMARY KEY (IDEmailProveedor),
    FOREIGN KEY (IDProveedor) REFERENCES Proveedores(IDProveedor)
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

call sp_EliminarProductos('P001');

-- Empleados
 
 delimiter $$

create procedure sp_AgregarEmpleados(in IDEmpleados int,in nombresEmpleado varchar(45), in apellidosEmpleado varchar(45), in sueldo decimal(10,2),in direccion varchar(150), in turno varchar(15), in IDCargoEmpleado int
)
begin
    insert into Empleados (IDEmpleados, nombresEmpleado, apellidosEmpleado, sueldo, direccion, turno, IDCargoEmpleado)
    values (IDEmpleados, nombresEmpleado, apellidosEmpleado, sueldo, direccion, turno, IDCargoEmpleado);
end$$

delimiter ;

call sp_AgregarEmpleados(1,'Juan', 'Gonzales', 1500.00, 'Avenida Lomas Turbias', 'Matutina', 2);
call sp_AgregarEmpleados(2,'Edgar', 'Morales', 2875.00, 'Avenida 12 calle 3 zona 15', 'vespertina', 2);

delimiter $$
create procedure sp_ListarEmpleados()
begin
    select IDEmpleados, nombresEmpleado, apellidosEmpleado, sueldo, direccion, turno, IDCargoEmpleado from Empleados;
end$$
delimiter ;

call sp_ListarEmpleados();

delimiter $$

create procedure sp_BuscarEmpleados(in _IDEmpleados int)
begin
    select * from Empleados where IDEmpleados = _IDEmpleados;
end$$

delimiter ;

call sp_BuscarProductos(1);

delimiter $$

create procedure sp_ActualizarEmpleados(
    in _IDEmpleados int, in nombresEmpleado varchar(45), in apellidosEmpleado varchar(45),
    in sueldo decimal(10,2), in direccion varchar(150), in turno varchar(15),
    in IDCargoEmpleado int)
begin
    update Empleados
    set
        nombresEmpleado = nombresEmpleado,
        apellidosEmpleado = apellidosEmpleado,
        sueldo = sueldo,
        direccion = direccion,
        IDCargoEmpleado = IDCargoEmpleado
    where
        IDEmpleados = _IDEmpleados;
end$$

delimiter ;

call sp_ActualizarEmpleados(2,'Juan', 'Gonzales', 1500.00, 'Avenida 12 calle 3 zona 15', 'Matutina', 2);

delimiter $$

create procedure sp_EliminarEmpleados(in _IDEmpleados int)
begin
    delete from Empleados where IDEmpleados = _IDEmpleados;
end$$

delimiter ;

call sp_EliminarEmpleados(1);

-- Factura
 
 delimiter $$

create procedure sp_AgregarFactura(in numFactura int,in estado varchar(45), in totalFactura decimal(10,2), in fechaFactura varchar(45),in IDClientes int, in IDEmpleados int
)
begin
    insert into Factura (numFactura, estado, totalFactura, fechaFactura, IDClientes, IDEmpleados)
    values (numFactura, estado, totalFactura, fechaFactura, IDClientes, IDEmpleados);
end$$

delimiter ;

call sp_AgregarFactura(1, 'Pendiente', 150.75, '2024-05-16', 2, 2);
call sp_AgregarFactura(2, 'Pagado', 140.00, '2024-05-16', 2, 2);

delimiter $$
create procedure sp_ListarFactura()
begin
    select numFactura, estado, totalFactura, fechaFactura, IDClientes, IDEmpleados from Factura;
end$$
delimiter ;

call sp_ListarFactura();

delimiter $$

create procedure sp_BuscarFactura(in _numFactura int)
begin
    select * from Factura where numFactura = _numFactura;
end$$

delimiter ;

call sp_BuscarFactura(1);

delimiter $$

create procedure sp_ActualizarFactura(
    in _numFactura int, in estado varchar(45), in totalFactura decimal(10,2),
    in fechaFactura varchar(45), in IDClientes int, in IDEmpleados int)
begin
    update Factura
    set
        estado = estado,
        totalFactura = totalFactura,
        fechaFactura = fechaFactura,
        IDClientes = IDClientes,
        IDEmpleados = IDEmpleados
    where
        numFactura = _numFactura;
end$$

delimiter ;

call sp_ActualizarFactura(2, 'Pendiente', 1500.00, '2024-05-16', 2, 2);

delimiter $$

create procedure sp_EliminarFactura(in _numFactura int)
begin
    delete from Factura where numFactura = _numFactura;
end$$

delimiter ;

call sp_EliminarFactura(1);

-- DetalleFactura
 
 delimiter $$

create procedure sp_AgregarDetalleFactura(in IDDetalleFactura int,in precioUnitario decimal(10,2), in cantidad int, in numFactura int,in IDProductos varchar(15)
)
begin
    insert into DetalleFactura (IDDetalleFactura, precioUnitario, cantidad, numFactura, IDProductos)
    values (IDDetalleFactura, precioUnitario, cantidad, numFactura, IDProductos);
end$$

delimiter ;

call sp_AgregarDetalleFactura(1, 1500.75, 2, 2, 'P002');
call sp_AgregarDetalleFactura(2, 150.75, 10, 2, 'P002');

delimiter $$
create procedure sp_ListarDetalleFactura()
begin
    select IDDetalleFactura, precioUnitario, cantidad, numFactura, IDProductos from DetalleFactura;
end$$
delimiter ;

call sp_ListarDetalleFactura();

delimiter $$

create procedure sp_BuscarDetalleFactura(in _IDDetalleFactura int)
begin
    select * from DetalleFactura where IDDetalleFactura = _IDDetalleFactura;
end$$

delimiter ;

call sp_BuscarDetalleFactura(1);

delimiter $$

create procedure sp_ActualizarDetalleFactura(
    in _IDDetalleFactura int,in precioUnitario decimal(10,2), in cantidad int, in numFactura int,in IDProductos varchar(15))
begin
    update DetalleFactura
    set
        precioUnitario = precioUnitario,
        cantidad = cantidad,
        numFactura = numFactura,
        IDProductos = IDProductos
    where
        IDDetalleFactura = _IDDetalleFactura;
end$$

delimiter ;

call sp_ActualizarDetalleFactura(2, 1500.00, 4, 2, 'P002');

delimiter $$

create procedure sp_EliminarDetalleFactura(in _IDDetalleFactura int)
begin
    delete from DetalleFactura where IDDetalleFactura = _IDDetalleFactura;
end$$

delimiter ;

call sp_EliminarDetalleFactura(1);

DELIMITER $$

CREATE TRIGGER CantidadTotalInsertar AFTER INSERT ON DetalleFactura
FOR EACH ROW
BEGIN
    UPDATE Productos
    SET existencia = existencia - NEW.cantidad
    WHERE IDProductos = NEW.IDProductos;
END
$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER CantidadTotalEliminar AFTER DELETE ON DetalleFactura
FOR EACH ROW
BEGIN
    UPDATE Productos
    SET existencia = existencia + OLD.cantidad
    WHERE IDProductos = OLD.IDProductos;
END
$$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER CantidadTotalActualizar AFTER UPDATE ON DetalleFactura
FOR EACH ROW
BEGIN
    DECLARE distincion INT;
    SET distincion = NEW.cantidad - OLD.cantidad;
    
    UPDATE Productos
    SET existencia = existencia - distincion
    WHERE IDProductos = NEW.IDProductos;
END
$$

DELIMITER ;

-- DetalleCompra
 
 delimiter $$

create procedure sp_AgregarDetalleCompra(in IDDetalleCompra int,in costoUnitario decimal(10,2), in cantidad int, in IDProductos varchar(15),in numDocumento int
)
begin
    insert into DetalleCompra (IDDetalleCompra, costoUnitario, cantidad, IDProductos, numDocumento)
    values (IDDetalleCompra, costoUnitario, cantidad, IDProductos, numDocumento);
end$$

delimiter ;

call sp_AgregarDetalleCompra(1, 15, 15, 'P002', 2);
call sp_AgregarDetalleCompra(2, 25, 10, 'P002', 2);

delimiter $$
create procedure sp_ListarDetalleCompra()
begin
    select IDDetalleCompra, costoUnitario, cantidad, IDProductos, numDocumento from DetalleCompra;
end$$
delimiter ;

call sp_ListarDetalleCompra();

delimiter $$

create procedure sp_BuscarDetalleCompra(in _IDDetalleCompra int)
begin
    select * from DetalleCompra where IDDetalleCompra = _IDDetalleCompra;
end$$

delimiter ;

call sp_BuscarDetalleCompra(1);

delimiter $$

create procedure sp_ActualizarDetalleCompra(
    in _IDDetalleCompra int,in costoUnitario decimal(10,2), in cantidad int, in IDProductos varchar(15),in numDocumento int)
begin
    update DetalleCompra
    set
        costoUnitario = costoUnitario,
        cantidad = cantidad,
        IDProductos = IDProductos,
        numDocumento = numDocumento
    where
        IDDetalleCompra = _IDDetalleCompra;
end$$

delimiter ;

call sp_ActualizarDetalleCompra(2,15, 7, 'P002', 2);

delimiter $$

create procedure sp_EliminarDetalleCompra(in _IDDetalleCompra int)
begin
    delete from DetalleCompra where IDDetalleCompra = _IDDetalleCompra;
end$$

delimiter ;

call sp_EliminarDetalleCompra(1);

DELIMITER $$
CREATE TRIGGER CFCompra AFTER INSERT ON DetalleCompra
FOR EACH ROW
BEGIN
    UPDATE Compras
    SET totalDocumento = totalDocumento + NEW.costoUnitario * NEW.cantidad
    WHERE numDocumento = NEW.numDocumento;
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER CFCompraActualizar AFTER UPDATE ON DetalleCompra
FOR EACH ROW
BEGIN
    UPDATE Compras
    SET totalDocumento = totalDocumento + (NEW.costoUnitario * NEW.cantidad) - (OLD.costoUnitario * OLD.cantidad)
    WHERE numDocumento = NEW.numDocumento;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER CFCompraEliminar AFTER DELETE ON DetalleCompra
FOR EACH ROW
BEGIN
    UPDATE Compras
    SET totalDocumento = totalDocumento - OLD.costoUnitario * OLD.cantidad
    WHERE numDocumento = OLD.numDocumento;
END
$$
DELIMITER ;

-- TelefonoProveedor
 
 delimiter $$

create procedure sp_AgregarTelefonoProveedor(in IDTelefonoProveedor int,in numeroPrincipal varchar(8), in numeroSecundario varchar(8), in observaciones varchar(45),in IDProveedor int
)
begin
    insert into TelefonoProveedor (IDTelefonoProveedor, numeroPrincipal, numeroSecundario, observaciones, IDProveedor)
    values (IDTelefonoProveedor, numeroPrincipal, numeroSecundario, observaciones, IDProveedor);
end$$

delimiter ;

call sp_AgregarTelefonoProveedor(1, '25698535', '14526987', 'Son ambos numeros personales', 2);
call sp_AgregarTelefonoProveedor(2, '12345678', '87654321', 'Son ambos numeros personales', 2);

delimiter $$
create procedure sp_ListarTelefonoProveedor()
begin
    select IDTelefonoProveedor, numeroPrincipal, numeroSecundario, observaciones, IDProveedor from TelefonoProveedor;
end$$
delimiter ;

call sp_ListarTelefonoProveedor();

delimiter $$

create procedure sp_BuscarTelefonoProveedor(in _IDTelefonoProveedor int)
begin
    select * from TelefonoProveedor where IDTelefonoProveedor = _IDTelefonoProveedor;
end$$

delimiter ;

call sp_BuscarTelefonoProveedor(1);

delimiter $$

create procedure sp_ActualizarTelefonoProveedor(
    in _IDTelefonoProveedor int,in numeroPrincipal varchar(8), in numeroSecundario varchar(8), in observaciones varchar(45),in IDProveedor int)
begin
    update TelefonoProveedor
    set
        numeroPrincipal = numeroPrincipal,
        numeroSecundario = numeroSecundario,
        observaciones = observaciones,
        IDProveedor = IDProveedor
    where
        IDTelefonoProveedor = _IDTelefonoProveedor;
end$$

delimiter ;

call sp_ActualizarTelefonoProveedor(2, '25698535', '14526987', 'Son ambos numeros personales', 2);

delimiter $$

create procedure sp_EliminarTelefonoProveedor(in _IDTelefonoProveedor int)
begin
    delete from TelefonoProveedor where IDTelefonoProveedor = _IDTelefonoProveedor;
end$$

delimiter ;

call sp_EliminarTelefonoProveedor(1);

-- EmailProveedor
 
 delimiter $$

create procedure sp_AgregarEmailProveedor(in IDEmailProveedor int,in emailProveedor varchar(45), in descripcion varchar(100), in IDProveedor int
)
begin
    insert into EmailProveedor (IDEmailProveedor, emailProveedor, descripcion, IDProveedor)
    values (IDEmailProveedor, emailProveedor, descripcion, IDProveedor);
end$$

delimiter ;

call sp_AgregarEmailProveedor(1, 'fgarcia@hotmail', 'correo Personal', 2);
call sp_AgregarEmailProveedor(2, 'fSicajau@hotmail', 'correo de empresa', 2);

delimiter $$
create procedure sp_ListarEmailProveedor()
begin
    select IDEmailProveedor, emailProveedor, descripcion, IDProveedor from EmailProveedor;
end$$
delimiter ;

call sp_ListarEmailProveedor();

delimiter $$

create procedure sp_BuscarEmailProveedor(in _IDEmailProveedor int)
begin
    select * from EmailProveedor where IDEmailProveedor = _IDEmailProveedor;
end$$

delimiter ;

call sp_BuscarEmailProveedor(1);

delimiter $$

create procedure sp_ActualizarEmailProveedor(
    in _IDEmailProveedor int,in emailProveedor varchar(45), in descripcion varchar(100), in IDProveedor int)
begin
    update EmailProveedor
    set
        emailProveedor = emailProveedor,
        descripcion = descripcion,
        IDProveedor = IDProveedor
    where
        IDEmailProveedor = _IDEmailProveedor;
end$$

delimiter ;

call sp_ActualizarEmailProveedor(2, 'fgarcia@hotmail', 'correo Personal', 2);

delimiter $$

create procedure sp_EliminarEmailProveedor(in _IDEmailProveedor int)
begin
    delete from EmailProveedor where IDEmailProveedor = _IDEmailProveedor;
end$$

delimiter ;

call sp_EliminarEmailProveedor(1);

-- -----------------------------------------
DELIMITER $$

CREATE TRIGGER CalcularPreciosDetalleCompra
AFTER INSERT ON DetalleCompra
FOR EACH ROW
BEGIN
    DECLARE precioProveedor DECIMAL(10,2);
    DECLARE precioDocena DECIMAL(10,2);
    DECLARE precioMayor DECIMAL(10,2);

    SET precioProveedor = NEW.costoUnitario * 1.40;
    SET precioDocena = precioProveedor * 1.35;
    SET precioMayor = precioProveedor * 1.25;

    UPDATE Productos
    SET precioUnitario = NEW.costoUnitario,
        precioDocena = precioDocena,
        precioMayor = precioMayor
    WHERE IDProductos = NEW.IDProductos;
END $$

DELIMITER ;