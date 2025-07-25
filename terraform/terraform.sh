#!/bin/bash

# Carrega as variáveis do arquivo .env
if [ -f .env ]; then
    export $(grep -v '^#' .env | xargs)
else
    echo "[terraform] Erro: Arquivo .env não encontrado."
    exit 1
fi

# Verifica se o método foi passado como argumento
if [ -z "$1" ]; then
    echo "[terraform] Erro: Nenhum método especificado (plan, apply, etc.)."
    exit 1
fi

METHOD=$1
shift

PARAMS="$@"

terraform $METHOD $PARAMS \
-var "aws_region=$AWS_REGION" \
-var "db_categoria_username=$DB_CATEGORIA_USERNAME" \
-var "db_categoria_password=$DB_CATEGORIA_PASSWORD" \
-var "db_categoria_identifier=$DB_CATEGORIA_IDENTIFIER" \
-var "db_categoria_name=$DB_CATEGORIA_NAME" \
-var "db_categoria_port=$DB_CATEGORIA_PORT" \
-var "dockerhub_username=$DOCKERHUB_USERNAME"