#include <fstream>
#include <iostream>
#include <Windows.h>
#include <commdlg.h>
#include <sstream>
#include <cstddef> 
#include <tileson.h>

namespace fisier {
	std::string fisierFolosit;
	bool fisierIncarcat = false;
    std::ofstream matrixFile("matrix.txt");
}
namespace maptrix {
    short int mapMatrix[10001][10001];
    int mWidth = 0, mHeight = 0;
}

void openFile()
{

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


    if (map->getStatus() == tson::ParseStatus::OK)
    {

        tson::Layer* layer = map->getLayer("WallLayer");

        if (layer->getType() == tson::LayerType::TileLayer)
        {
            width= map->getSize().x;
            height = map->getSize().y;
            //pos = position in tile units
            for (auto& [pos, tileObject] : layer->getTileObjects()) //Loops through absolutely all existing tileObjects
            {
                tson::Tileset* tileset = tileObject.getTile()->getTileset();
                std::cout << tileset->getName() << " ";

                tson::Vector2f position = tileObject.getPosition();
                int pX = position.x;
                int pY = position.y;
                std::cout << position.x << " " << position.y << '\n';

                if (tileset->getName() == "perete") {
                    maptrix::mapMatrix[pY / 64][pX / 64] = -1;
                }
                

            }
        }

    }

    fout << "{\n";
    for (int i = 0; i < height; i++) {
        fout << "{";
        int j;
        for ( j= 0; j < width; j++) {
            fout << mapMatrix[i][j]<<" ";
            if (j < width - 1) {
                fout << ",";
            }
        }
        if (j == width - 1 && i == height - 1) {
            fout << "}";
        }
        else if (j == width - 1) {
           fout << "}";
        }
        else {
           fout << "},";
        }
        
        fout << "\n";

    }
    fout << "}";
}


int main() {
	openFile();


	tson::Tileson t;
	std::unique_ptr<tson::Map> map = t.parse(fs::path(fisier::fisierFolosit));
    

    matrixGen(map,maptrix::mapMatrix, maptrix::mWidth, maptrix::mHeight, fisier::matrixFile);


        return 0;
}