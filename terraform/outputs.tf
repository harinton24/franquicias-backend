output "acr_login_server" {
  value       = azurerm_container_registry.acr.login_server
  description = "Dirección del registro de contenedores de Azure para subir la imagen de Spring"
}

output "api_public_url" {
  value       = "https://${azurerm_linux_web_app.backend_app.default_hostname}"
  description = "URL pública de tu API de Spring Boot en Azure"
}

output "sql_server_fqdn" {
  value       = azurerm_mssql_server.sql_server.fully_qualified_domain_name
  description = "Endpoint del servidor de base de datos SQL Server"
}