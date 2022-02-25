#include <fstream>
#include <iostream>
#include <Windows.h>
#include <commdlg.h>
#include <sstream>
#include <cstddef> 
#include <tileson.h>
#include <stdlib.h>
#include <string>

namespace fisier {
	std::string fisierFolosit;
	bool fisierIncarcat = false;
    std::ofstream matrixFile("Matrix.txt");
    std::ofstream wallsFile("Walls.txt");
}
namespace maptrix {
    short int mapMatrix[10001][10001];
    int mWidth = 0, mHeight = 0;
}

void openFile(){

	OPENFILENAMEA ofn;
	CHAR szFile[260] = { 0 };
	CHAR currentDir[256] = { 0 };
	ZeroMemory(&ofn, sizeof(OPENFILENAME));
	ofn.lStructSize = sizeof(OPENFILENAME);
	ofn.lpstrFile = szFile;
	ofn.nMaxFile = sizeof(szFile);

	if (GetCurrentDirectoryA(256, currentDir)) {
		ofn.lpstrInitialDir = currentDir;
	}

	ofn.nFilterIndex = 1;
	ofn.Flags = OFN_PATHMUSTEXIST | OFN_FILEMUSTEXIST | OFN_NOCHANGEDIR;



	if (GetOpenFileNameA(&ofn) == TRUE) {
		fisier::fisierFolosit = ofn.lpstrFile;
		fisier::fisierIncarcat = true;
		return;

	}

	fisier::fisierIncarcat = false;

}


void matrixGen(std::unique_ptr<tson::Map>& map, short int mapMatrix[10001][10001], int width, int height, std::ofstream& fout) {


    if (map->getStatus() == tson::ParseStatus::OK){

        tson::Layer* layer = map->getLayer("WallLayer1");

        if (layer->getType() == tson::LayerType::TileLayer){
            width= map->getSize().x;
            height = map->getSize().y;

            //pos = position in tile units
            for (auto& [pos, tileObject] : layer->getTileObjects()) //Loops through absolutely all existing tileObjects
            {
                tson::Tileset* tileset = tileObject.getTile()->getTileset();
                //std::cout << tileset->getName() << " ";

                tson::Vector2f position = tileObject.getPosition();
                int pX = position.x;
                int pY = position.y;
                //std::cout << position.x << " " << position.y << '\n';

                if (tileset->getName() == "perete"  ||
                    tileset->getName() == "pereteW" ||
                    tileset->getName() == "pereteA" ||
                    tileset->getName() == "pereteS" ||
                    tileset->getName() == "pereteD" 
                    ) {
                    maptrix::mapMatrix[pY / 64][pX / 64] = -1;
                }
                

            }
        }
        layer = map->getLayer("WallLayer2");

        if (layer->getType() == tson::LayerType::TileLayer) {
            width = map->getSize().x;
            height = map->getSize().y;

            //pos = position in tile units
            for (auto& [pos, tileObject] : layer->getTileObjects()) //Loops through absolutely all existing tileObjects
            {
                tson::Tileset* tileset = tileObject.getTile()->getTileset();
                //std::cout << tileset->getName() << " ";

                tson::Vector2f position = tileObject.getPosition();
                int pX = position.x;
                int pY = position.y;
                //std::cout << position.x << " " << position.y << '\n';

                if (tileset->getName() == "perete" ||
                    tileset->getName() == "pereteW" ||
                    tileset->getName() == "pereteA" ||
                    tileset->getName() == "pereteS" ||
                    tileset->getName() == "pereteD"
                    ) {
                    maptrix::mapMatrix[pY / 64][pX / 64] = -1;
                }


            }
        }

    }


    //fout << "{\n";
    for (int i = 0; i < height; i++) {
        //fout << "{";
        int j;
        for ( j= 0; j < width; j++) {
            fout << mapMatrix[i][j];
          //  if (mapMatrix[i][j] == 0) {
           //     fout << " ";
          //  }
            fout<<" ";
          //  if (j < width - 1) {
           //     fout << ",";
           // }
        }
      //  if (j == width - 1 && i == height - 1) {
       //     fout << "}";
      //  }
      //  else if (j == width - 1) {
       //    fout << "}";
     //   }
      //  else {
        //   fout << "},";
      //  }
        
        fout << "\n";

    }
    //fout << "}";
}


void wallsGen(std::unique_ptr<tson::Map>& map, std::ofstream& wout) {
    if (map->getStatus() == tson::ParseStatus::OK) {

int nr = 1;
wout << "\n\n\npublic void init" << nr << "(){" << "\n";
nr++;

        tson::Layer* layer = map->getLayer("WallLayer1"); ///walls on floor 1
wout << R"(//Walls)"<<"\n";
        if (layer->getType() == tson::LayerType::TileLayer) {
            
            int n = 0;
            
            //pos = position in tile units
            for (auto& [pos, tileObject] : layer->getTileObjects()) //Loops through absolutely all existing tileObjects
            {

                n++;
                if (n >= 1500) {
                   
                    n = 0;
                    wout << "\n}\n\n\n\n";
                    wout << "\n\n\npublic void init" << nr << "(){" << "\n"; 
                    nr++;
                }
                tson::Tileset* tileset = tileObject.getTile()->getTileset();
                
                tson::Vector2f position = tileObject.getPosition();
                int pX = position.x;
                int pY = position.y;

                if (tileset->getName() == "pereteW") {
                    wout << R"(world.initObject(new PereteInvizibil("W", 1,"mic"), )" << pX + 32 << ", " << pY + 8 << ");\n";
                }
                if (tileset->getName() == "pereteA") {
                    wout << R"(world.initObject(new PereteInvizibil("A", 1,"mic90"), )" << pX + 8 << ", " << pY + 32 << ");\n";
                }
                if (tileset->getName() == "pereteS") {
                    wout << R"(world.initObject(new PereteInvizibil("S", 1,"mic"), )" << pX + 32 << ", " << pY + 56 << ");\n";
                }
                if (tileset->getName() == "pereteD") {
                    wout << R"(world.initObject(new PereteInvizibil("D", 1,"mic90"), )" << pX + 56 << ", " << pY + 32 << ");\n";
                }


                /// world.initObject(new PereteInvizibil("W", 1, "mic"), i, 16);
                /// world.initObject(new PereteInvizibil("A", 1, "mic90"), i, 16);

            }
        }
        layer = map->getLayer("WallLayer2"); ///walls on floor 1

        if (layer->getType() == tson::LayerType::TileLayer) {
            int n = 0;
            
            //pos = position in tile units
            for (auto& [pos, tileObject] : layer->getTileObjects()) //Loops through absolutely all existing tileObjects
            {
                n++;
                if (n >= 1500) {
                    
                    n = 0;
                    wout << "\n}\n\n\n\n";
                    wout << "\n\n\npublic void init"<<nr<<"(){" << "\n";
                    nr++;
                }
                tson::Tileset* tileset = tileObject.getTile()->getTileset();

                tson::Vector2f position = tileObject.getPosition();
                int pX = position.x;
                int pY = position.y;

                if (tileset->getName() == "pereteW") {
                    wout << R"(world.initObject(new PereteInvizibil("W", 1,"mic"), )" << pX+32 << ", " << pY +8 << ");\n";
                }
                if (tileset->getName() == "pereteA") {
                    wout << R"(world.initObject(new PereteInvizibil("A", 1,"mic90"), )" << pX +8 << ", " << pY+32 << ");\n";
                }
                if (tileset->getName() == "pereteS") {
                    wout << R"(world.initObject(new PereteInvizibil("S", 1,"mic"), )" << pX+32 << ", " << pY + 56 << ");\n";
                }
                if (tileset->getName() == "pereteD") {
                    wout << R"(world.initObject(new PereteInvizibil("D", 1,"mic90"), )" << pX + 56 << ", " << pY+32 << ");\n";
                }


                /// world.initObject(new PereteInvizibil("W", 1, "mic"), i, 16);
                /// world.initObject(new PereteInvizibil("A", 1, "mic90"), i, 16);

            }
        }
        wout << R"(//Walls)" << "\n}";


    }
}

void npcGen(std::unique_ptr<tson::Map>& map, std::ofstream& wout){
    if (map->getStatus() == tson::ParseStatus::OK) {

        tson::Layer* layer = map->getLayer("NpcLayer"); ///walls on floor 1

        if (layer->getType() == tson::LayerType::TileLayer) {

            wout << R"(public void initNpc(){)" << "\n";
            wout << R"(//npc)" << "\n";
            //pos = position in tile units
            for (auto& [pos, tileObject] : layer->getTileObjects()) //Loops through absolutely all existing tileObjects
            {
                tson::Tileset* tileset = tileObject.getTile()->getTileset();

                tson::Vector2f position = tileObject.getPosition();
                int pX = position.x;
                int pY = position.y;

                if (tileset->getName() == "zGoblin") {
                    wout << R"(world.initObject(new Goblin(scroller, )" << pX  << ", " << pY  << "),"<<pX<<", "<<pY<<");\n";
                }


                if (tileset->getName() == "zCat") {
                    wout << R"(world.initObject(new SchrodingersCat(scroller, )" << pX << ", " << pY << ")," << pX << ", " << pY << ");\n";
                }


                if (tileset->getName() == "zDolpatian") {
                    wout << R"(world.initObject(new Dolpatian(scroller, )" << pX << ", " << pY << ")," << pX << ", " << pY << ");\n";
                }

                if (tileset->getName() == "zDroid") {
                    
                    int xy = rand() ;
                    std::string XY="";
                    int dist;
                    if (xy % 2 == 0) {
                        XY = "ox";
                        dist = 600;
                    }
                    else {
                        XY = "oy";
                        dist = 400;
                    }
                    wout << R"(world.initObject(new Droid(scroller, )" << pX << ", " << pY << ", \""<<XY<<"\","<<dist<<"),"<< pX << ", " << pY << ");\n";
                }


                /// world.initObject(new PereteInvizibil("W", 1, "mic"), i, 16);
                /// world.initObject(new PereteInvizibil("A", 1, "mic90"), i, 16);

            }
            wout << R"(//npc)" << "\n";
            wout << "}\n";
        }
    }
}



int main() {
	openFile();
    srand(time(0));

	tson::Tileson t;
	std::unique_ptr<tson::Map> map = t.parse(fs::path(fisier::fisierFolosit));
    
    int caz;
    std::cout << " Npc matrix press 1 \n Worldcode press 2\n "; std::cin >> caz;

    switch (caz) {
    case 1: {
        matrixGen(map, maptrix::mapMatrix, maptrix::mWidth, maptrix::mHeight, fisier::matrixFile);
        break;
    }
    case 2: {
        
        npcGen(map, fisier::wallsFile);
        wallsGen(map, fisier::wallsFile);
        break;
    }
    }



        return 0;
}