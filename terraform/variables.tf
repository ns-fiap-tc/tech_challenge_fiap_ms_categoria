# AWS provider configuration
variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

# Database categoria configuration
variable "db_categoria_username" {
  description = "The username for the RDS categoria instance"
  type        = string
  sensitive   = true
}

variable "db_categoria_password" {
  description = "The password for the RDS categoria instance"
  type        = string
  sensitive   = true
}

variable "db_categoria_name" {
  description = "Database categoria name"
  type        = string
  default     = "lanch_cat_db"
}

variable "db_categoria_port" {
  description = "Database categoria port"
  type        = string
  default     = "5432"
}

variable "db_categoria_identifier" {
  description = "The identifier for the RDS categoria instance"
  type        = string
  default     = "lanchonete-categoria-db"
}

#Variaveis DockerHUB

variable "dockerhub_username" {
  description = "The username of the dockerhub image to deploy"
  type        = string
}

/*variable "dockerhub_token" {
  description = "The access token of the dockerhub image to deploy"
  type        = string
}*/