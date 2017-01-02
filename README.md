# Pathfinding API

a REST Web Service written in Spring/Java

## Installation



## Documentation

### List Algorithms
----
  returns an array of algorithm names available for use.

* **URL:** /api/algorithms

* **Method:** `GET`
  
*  **URL Params**

* **Data Params**

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 

    in json:
    
    ```json
    [
        "depthfirst",
        "breadthfirst",
        "greedybestfirst"
    ]
    ```
 
* **Sample Call:**

  ```javascript
  ```
  
  ```sh
  ```

* **Notes:**

### Solve Pathfinding Problem
----
  
  finds the solution/path for the given problem, defined by the request body. 

* **URL:** /api/solve/:algorithm

* **Method:** `POST`
  
*  **URL Params**

* **Data Params**

  * width=[integer]
  * height=[integer]
  * start=[object/coordinate]
  * goal=[object/coordinate]
  * obstacles=[array:object/coordinate]
    

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 

    in json:
    
    ```json
    ```
 
* **Sample Call:**

  ```javascript
  ```
  
  ```sh
  ```

* **Notes:**