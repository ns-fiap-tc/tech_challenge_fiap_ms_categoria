resource "kubernetes_secret" "secrets-categoria" {
  metadata {
    name = "secrets-categoria"
  }

  type = "Opaque"

  data = {
    DB_HOST             = element(split(":",data.aws_db_instance.lanchonete_categoria_db.endpoint),0)
    DB_PORT             = var.db_categoria_port
    DB_NAME             = var.db_categoria_name
    DB_USER             = var.db_categoria_username
    DB_PASSWORD         = var.db_categoria_password
  }

  lifecycle {
    prevent_destroy = false
  }
}

# MS CATEGORIA 
resource "kubernetes_deployment" "deployment-ms-categoria" {
  metadata {
    name      = "deployment-ms-categoria"
    namespace = "default"
  }

  spec {
    selector {
      match_labels = {
        app = "deployment-ms-categoria"
      }
    }

    template {
      metadata {
        labels = {
          app = "deployment-ms-categoria"
        }
      }

      spec {
        toleration {
          key      = "key"
          operator = "Equal"
          value    = "value"
          effect   = "NoSchedule"
        }

        container {
          name  = "deployment-ms-categoria-container"
          image = "${var.dockerhub_username}/fiap-tech-challenge-lanchonete-ms-categoria:latest"

          resources {
            requests = {
              memory : "512Mi"
              cpu : "500m"
            }
            limits = {
              memory = "1Gi"
              cpu    = "1"
            }
          }

          env_from {
            secret_ref {
              name = kubernetes_secret.secrets-categoria.metadata[0].name
            }
          }

          port {
            container_port = "8080"
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "service-ms-categoria" {
  metadata {
    name      = "service-ms-categoria"
    namespace = "default"
    annotations = {
      "service.beta.kubernetes.io/aws-load-balancer-type" : "nlb",
      "service.beta.kubernetes.io/aws-load-balancer-scheme" : "internal",
      "service.beta.kubernetes.io/aws-load-balancer-cross-zone-load-balancing-enabled" : "true"
    }
  }
  spec {
    selector = {
      app = "deployment-ms-categoria"
    }
    port {
      port = "80"
      target_port = "8080"
      node_port = "30001"
    }
    type = "LoadBalancer"
  }
}
