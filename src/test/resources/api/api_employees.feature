@api
Feature: Testes de API relacionados aos métodos do endpoint de '/api/v1/employees'
  - http://dummy.restapiexample.com/api/v1/employees
  - http://dummy.restapiexample.com/api/v1/employee/{id}
  - http://dummy.restapiexample.com/api/v1/create
  - http://dummy.restapiexample.com/api/v1/update/{id}
  - http://dummy.restapiexample.com/api/v1/delete/{id}

  Scenario: Validação do corpo de resposta ao buscar por TODOS os empregados
    When uma requisição "GET" é efetuada para "http://dummy.restapiexample.com/api/v1/employees"
    Then o status da resposta é 200
    And o corpo de resposta deve conter o schema
    """
    {
      "$schema": "http://json-schema.org/draft-04/schema#",
      "type": "object",
      "properties": {
        "status": {
          "type": "string"
        },
        "data": {
          "type": "array",
          "items": [
            {
              "type": "object",
              "properties": {
                "id": {
                  "type": "integer"
                },
                "employee_name": {
                  "type": "string"
                },
                "employee_salary": {
                  "type": "integer"
                },
                "employee_age": {
                  "type": "integer"
                },
                "profile_image": {
                  "type": "string"
                }
              },
              "required": [
                "id",
                "employee_name",
                "employee_salary",
                "employee_age",
                "profile_image"
              ]
            }
          ]
        },
        "message": {
          "type": "string"
        }
      },
      "required": [
        "status",
        "data",
        "message"
      ]
    }
    """

  Scenario: Validação do corpo de resposta ao buscar com empregado
    When uma requisição "GET" é efetuada para "http://dummy.restapiexample.com/api/v1/employee/1"
    Then o status da resposta é 200
    And o corpo de resposta deve conter o schema
    """
    {
      "$schema": "http://json-schema.org/draft-04/schema#",
      "type": "object",
      "properties": {
        "status": {
          "type": "string"
        },
        "data": {
          "type": "object",
          "properties": {
            "id": {
              "type": "integer"
            },
            "employee_name": {
              "type": "string"
            },
            "employee_salary": {
              "type": "integer"
            },
            "employee_age": {
              "type": "integer"
            },
            "profile_image": {
              "type": "string"
            }
          },
          "required": [
            "id",
            "employee_name",
            "employee_salary",
            "employee_age",
            "profile_image"
          ]
        },
        "message": {
          "type": "string"
        }
      },
      "required": [
        "status",
        "data",
        "message"
      ]
    }
    """

  Scenario: validação do corpo de reposta ao criar empregado
    Given um empregado gerado randomicamente
    When uma requisição "POST" é efetuada para "http://dummy.restapiexample.com/api/v1/create"
    Then o status da resposta é 200
    And o corpo de resposta deve conter o schema
    """
    {
      "$schema": "http://json-schema.org/draft-04/schema#",
      "type": "object",
      "properties": {
        "status": {
          "type": "string"
        },
        "data": {
          "type": "object",
          "properties": {
            "name": {
              "type": "string"
            },
            "salary": {
              "type": "integer"
            },
            "age": {
              "type": "integer"
            },
            "profileImageUrl": {
              "type": "null"
            },
            "id": {
              "type": "integer"
            }
          },
          "required": [
            "name",
            "salary",
            "age",
            "profileImageUrl",
            "id"
          ]
        },
        "message": {
          "type": "string"
        }
      },
      "required": [
        "status",
        "data",
        "message"
      ]
    }
    """

  Scenario: Validação de corpo de resposta ao alterar dados de empregado
    Given um empregado gerado randomicamente
    And uma requisição "POST" é efetuada para "http://dummy.restapiexample.com/api/v1/create"
    And o nome do empregado é alterado
      | employee_name | employee_salary | employee_age | profile_image |
      | Edit {UUID}   | 99999           | {random_age} |               |
    When uma requisição "PUT" é efetuada para "http://dummy.restapiexample.com/api/v1/update/{id}"
    Then o status da resposta é 200
    And o corpo de resposta deve conter o schema
    """
    {
      "$schema": "http://json-schema.org/draft-04/schema#",
      "type": "object",
      "properties": {
        "status": {
          "type": "string"
        },
        "data": {
          "type": "object",
          "properties": {
            "name": {
              "type": "string"
            },
            "salary": {
              "type": "integer"
            },
            "age": {
              "type": "integer"
            },
            "profileImageUrl": {
              "type": "null"
            },
            "id": {
              "type": "integer"
            }
          },
          "required": [
            "name",
            "salary",
            "age",
            "profileImageUrl",
            "id"
          ]
        },
        "message": {
          "type": "string"
        }
      },
      "required": [
        "status",
        "data",
        "message"
      ]
    }
    """

  Scenario: Validação de corpo de resposta ao remover os dados de empregado
    Given um empregado gerado randomicamente
    And uma requisição "POST" é efetuada para "http://dummy.restapiexample.com/api/v1/create"
    When uma requisição "DELETE" é efetuada para "http://dummy.restapiexample.com/api/v1/delete/{id}"
    Then o status da resposta é 200
    And o corpo de resposta deve conter o schema
    """
    {
      "$schema": "http://json-schema.org/draft-04/schema#",
      "type": "object",
      "properties": {
        "status": {
          "type": "string"
        },
        "data": {
          "type": "string"
        },
        "message": {
          "type": "string"
        }
      },
      "required": [
        "status",
        "data",
        "message"
      ]
    }
    """