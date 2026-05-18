variable "project_name" {
  type        = string
  description = "Nombre base para los recursos del proyecto"
}

variable "environment" {
  type        = string
  description = "Entorno de despliegue"
}

variable "location" {
  type        = string
  description = "Región de Azure donde se crearán los recursos"
}

variable "db_admin_username" {
  type        = string
  description = "Usuario administrador para SQL Server"
}

variable "db_admin_password" {
  type        = string
  description = "Contraseña para el administrador de SQL Server"
}