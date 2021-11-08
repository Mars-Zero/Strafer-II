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


int main() {
	openFile();


	tson::Tileson t;
	std::unique_ptr<tson::Map> map = t.parse(fisier::fisierFolosit);
    if (map->getStatus() == tson::ParseStatus::OK)
    {
		std::cout << "aaaaa";
       
       
    } 
        return 0;
}