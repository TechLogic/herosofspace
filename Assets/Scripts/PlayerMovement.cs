using UnityEngine;
using System.Collections;


public class PlayerMovement : MonoBehaviour {

	public float acceleration = 1;
    public Vector3 MaxSpeed = new Vector3(25f,25f,25f);
    public Vector3 Velocity;
 


	void FixedUpdate(){
		float x = Input.GetAxis("X");
		float y = Input.GetAxis("Y");
		float z = Input.GetAxis("Z");
		
		MovementManagement(x,y,z);

	}




	void MovementManagement(float x,float y, float z){

		Vector3 movement = new Vector3(x,y,z);
		movement *= acceleration;

   
        if (Mathf.Abs(rigidbody.velocity.x + movement.x) >= MaxSpeed.x)
        {
           movement.x = 0;
        }
        if (Mathf.Abs(rigidbody.velocity.y + movement.x) >= MaxSpeed.y)
        {
            movement.y = 0;
        }
        if (Mathf.Abs(rigidbody.velocity.z+ movement.z) >= MaxSpeed.z)
        {
            movement.z = 0;
        }


        rigidbody.AddForce(movement);
        Velocity = rigidbody.velocity;
       
       
        


	}








}
