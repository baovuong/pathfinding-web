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

  JSON Object:
  * width=[integer]
  * height=[integer]
  * start=[object/coordinate]
  * goal=[object/coordinate]
  * obstacles=[array:object/coordinate] (optional)
  
  Example:
  
  ```json
  {
    "width": 10,
    "height": 10,
    "start": {
        "x": 1,
        "y": 1,
    },
    "goal": {
        "x": 10,
        "y": 10,
    },
    "obstacles": [
        {
            "x": 5,
            "y": 5
        }
  }
  ```

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