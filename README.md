# parking-garage

Parking garage provides services to park and unpark the vehicle based on availability

1) Requirements:

Functional Requirements:
Vehicle entry/exit - there can be many ways -licence plate read and note its time of entry and exit and bill accordingly
or on entry can be issued ticket and on exit accordingly bill
Bill on exit
Parking parkingSpotDTO availability like on which floor how many spots are available
Special assistance let say near elevators
Parking spots based on vehicle type- sedan, suv, truck
Parking rates let say can be hourly basis flat rate or daily pass, or monthly pass

Non-Functional Requirements:
99.99% availability
Fault tolerant
Scalable
Low latency- shouldn’t take more time to give response

2) Database Schemas:
   Vehicle - id, type(sedan, suv, truck), licence plate no, user_id
   Parking parkingSpotDTO- id, status(occupied, reserved, available), type(sedan, suv, truck), floor no
   User - id, name, email, phone no
   Ticket - id, spot_id, entrytime, exittime, vehicle_id,total_fee
   Payment- id, user_id, amount_paid, payment_mode, paymentTime

3) End points:
   User service
   /api/users - POST- create users requestBody-User
   /api/users/{id}-GET - get user details
   /api/users/{id}vehicle- POST - add vehicle to user info - requestBody- vehicle

Parking service
/api/parking/addSpot - POST - add parking spots
/api/parking/availableSpots -GET - fetch available parking spots
/api/parking/ticket/entry - POST - create new ticket
/api/parking/ticket/{id}/exit - POST- close the ticket and exit and do payment

Payment service
/api/payments - POST- make payment
/api/payments/{id}-GET - get payment details

Notification service
/api/notification/send - POST- send notification via email or text
/api/notification/users{id}-GEt - get notification details of userId


   

