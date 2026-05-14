CREATE TABLE franquicias (
                             id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                             nombre NVARCHAR(255) NOT NULL
);

CREATE TABLE sucursales (
                            id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                            franquicia_id UNIQUEIDENTIFIER NOT NULL,
                            nombre NVARCHAR(255) NOT NULL,
                            CONSTRAINT FK_Sucursales_Franquicias FOREIGN KEY (franquicia_id) REFERENCES franquicias(id) ON DELETE CASCADE
);

CREATE TABLE productos (
                           id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
                           sucursal_id UNIQUEIDENTIFIER NOT NULL,
                           nombre NVARCHAR(255) NOT NULL,
                           stock INT DEFAULT 0,
                           CONSTRAINT FK_Productos_Sucursales FOREIGN KEY (sucursal_id) REFERENCES sucursales(id) ON DELETE CASCADE
);