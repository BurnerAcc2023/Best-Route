Assumptions:
1. A Restaurant can accept only one order.
2. A Customer can only have one current order.
3. A Delivery Executive can at a time deliver only one order.

For getting the best route, start the service and make a post call to "/api/v1/best-route".

Sample Request
```
[
    {
        "customerId" : 1,
        "restaurantId" : 1,
        "preparationTime" : 10,
        "deliveryExecutiveId" : 1
    },
    {
        "customerId" : 2,
        "restaurantId" : 2,
        "preparationTime" : 5,
        "deliveryExecutiveId" : 1
    }
]
```

You can add more orders in this format. 

To change location or add more restaurants/customers, go to src/main/resources/ and then modify the ExampleData.jsons for each Entity.
