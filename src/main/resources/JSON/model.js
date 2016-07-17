model1 = {
    version: '1.0',
    defaultSchema: 'SALES',
    schemas: [
        {
            name: 'SALES',
            type: 'custom',
            factory: 'org.apache.calcite.adapter.csv.CsvSchemaFactory',
            operand: {
                directory: 'target/test-classes/sales'
            }
        }
    ]
}

model2 = {
    version: '1.0',
    defaultSchema: 'SALES',
    schemas: [
        {
            name: 'SALES',
            type: 'custom',
            factory: 'org.apache.calcite.adapter.csv.CsvSchemaFactory',
            operand: {
                directory: 'target/test-classes/sales'
            },
            tables: [
                {
                    name: 'FEMALE_EMPS',
                    type: 'view',
                    sql: 'SELECT * FROM emps WHERE gender = \'F\''
                }
            ]
        }
    ]
}

model3 = {
    version: '1.0',
    defaultSchema: 'SALES',
    schemas: [
        {
            name: 'SALES',
            type: 'custom',
            factory: 'org.apache.calcite.adapter.csv.CsvSchemaFactory',
            operand: {
                directory: 'target/test-classes/sales'
            },
            tables: [
                {
                    name: 'FEMALE_EMPS',
                    type: 'view',
                    sql: [
                        'SELECT * FROM emps',
                        'WHERE gender = \'F\''
                    ]
                }
            ]
        }
    ]
}

model4 = {
    version: '1.0',
    defaultSchema: 'CUSTOM_TABLE',
    schemas: [
        {
            name: 'CUSTOM_TABLE',
            tables: [
                {
                    name: 'EMPS',
                    type: 'custom',
                    factory: 'org.apache.calcite.adapter.csv.CsvTableFactory',
                    operand: {
                        file: 'target/test-classes/sales/EMPS.csv.gz',
                        flavor: "scannable"
                    }
                }
            ]
        }
    ]
}

model5 = { //JDBC adapter
    version: '1.0',
    defaultSchema: 'FOODMART',
    schemas: [
        {
            name: 'FOODMART',
            type: 'custom',
            factory: 'org.apache.calcite.adapter.jdbc.JdbcSchema$Factory',
            operand: {
                jdbcDriver: 'com.mysql.jdbc.Driver',
                jdbcUrl: 'jdbc:mysql://localhost/foodmart',
                jdbcUser: 'foodmart',
                jdbcPassword: 'foodmart'
            }
        }
    ]
}

mpdel6 = { //The cloning JDBC adapter
    version: '1.0',
        defaultSchema: 'FOODMART_CLONE',
    schemas: [
    {
        name: 'FOODMART_CLONE',
        type: 'custom',
        factory: 'org.apache.calcite.adapter.clone.CloneSchema$Factory',
        operand: {
            jdbcDriver: 'com.mysql.jdbc.Driver',
            jdbcUrl: 'jdbc:mysql://localhost/foodmart',
            jdbcUser: 'foodmart',
            jdbcPassword: 'foodmart'
        }
    }
]
}

model7 = {
    version: '1.0',
    defaultSchema: 'FOODMART_CLONE',
    schemas: [
        {
            name: 'FOODMART',
            type: 'custom',
            factory: 'org.apache.calcite.adapter.jdbc.JdbcSchema$Factory',
            operand: {
                jdbcDriver: 'com.mysql.jdbc.Driver',
                jdbcUrl: 'jdbc:mysql://localhost/foodmart',
                jdbcUser: 'foodmart',
                jdbcPassword: 'foodmart'
            }
        },
        {
            name: 'FOODMART_CLONE',
            type: 'custom',
            factory: 'org.apache.calcite.adapter.clone.CloneSchema$Factory',
            operand: {
                source: 'FOODMART'
            }
        }
    ]
}