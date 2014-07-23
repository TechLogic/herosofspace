using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using NoiseGeneration;
using MeshBuilding;

public class AsteroidMeshGeneration : MonoBehaviour
{

    public int width = 500;
    public float spacing = 1f;
    public float maxHeight = 3f;
    public MeshFilter terrainMesh = null;
    float[] noise = null;


    float GetHeight(float x_coor, float z_coor)
    {
        return 0;
    }

    void Start()
    {
        if (terrainMesh == null)
        {
            Debug.LogError("ProceduralTerrain requires its target terrainMesh to be assigned.");
        }

        GenerateMesh();
    }

    void GenerateMesh()
    {
        float start_time = Time.time;

        NoiseGenerator gen = new NoiseGenerator();
        noise = gen.generateNoise(width,width,100);
        MeshBuilder builder = new MeshBuilder();

        Vector3 offset = new Vector3(0, 0, 0);

        for(int x = 0;x< width;x++){

            for(int z=0;z< width;z++){
                offset.z+= spacing;
                offset.y = noise[x * width + z] * Random.Range(-3,3);
                bool tri = x > 0 && z > 0;
                BuildQuadForGrid(builder,offset,new Vector2((1f/width)*x,(1f/width)*z),tri,width);
            }
            offset.x+= spacing;
            offset.z = 0;
        }
        

        if (terrainMesh != null)
        {
            Mesh mesh = builder.CreateMesh();
            mesh.RecalculateNormals();
            terrainMesh.sharedMesh = mesh; 
        }
       
     

        float diff = Time.time - start_time;
        Debug.Log("ProceduralTerrain was generated in " + diff + " seconds.");
    }

    void BuildQuad(MeshBuilder meshBuilder, Vector3 offset)
{
	meshBuilder.Vertices.Add(new Vector3(0.0f, 0.0f, 0.0f) + offset);
	meshBuilder.UVs.Add(new Vector2(0.0f, 0.0f));
	meshBuilder.Normals.Add(Vector3.up);

    meshBuilder.Vertices.Add(new Vector3(0.0f, 0.0f, spacing) + offset);
	meshBuilder.UVs.Add(new Vector2(0.0f, 1.0f));
	meshBuilder.Normals.Add(Vector3.up);

    meshBuilder.Vertices.Add(new Vector3(spacing, 0.0f, spacing) + offset);
	meshBuilder.UVs.Add(new Vector2(1.0f, 1.0f));
	meshBuilder.Normals.Add(Vector3.up);

    meshBuilder.Vertices.Add(new Vector3(spacing, 0.0f, 0.0f) + offset);
	meshBuilder.UVs.Add(new Vector2(1.0f, 0.0f));
	meshBuilder.Normals.Add(Vector3.up);

	int baseIndex = meshBuilder.Vertices.Count - 4;

	meshBuilder.AddTriangle(baseIndex, baseIndex + 1, baseIndex + 2);
	meshBuilder.AddTriangle(baseIndex, baseIndex + 2, baseIndex + 3);
}

    void BuildQuadForGrid(MeshBuilder meshBuilder, Vector3 position, Vector2 uv,
    bool buildTriangles, int vertsPerRow)
    {
        meshBuilder.Vertices.Add(position);
        meshBuilder.UVs.Add(uv);

        if (buildTriangles)
        {
            int baseIndex = meshBuilder.Vertices.Count - 1;

            int index0 = baseIndex;
            int index1 = baseIndex - 1;
            int index2 = baseIndex - vertsPerRow;
            int index3 = baseIndex - vertsPerRow - 1;

            meshBuilder.AddTriangle(index0, index2, index1);
            meshBuilder.AddTriangle(index2, index3, index1);
        }
    }

}



