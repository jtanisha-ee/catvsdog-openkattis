#include <iostream>
#include <vector>

using namespace std;

class Person {
        public:
                int keep;
                int leave;
}

int main() {
        // Defining variables and taking input
        int numTests;
        int cats;
        int dogs;
        int voters;
        vector <int> people;

        cin >> numTests;

        for (int i=0; i < numTests; i ++){
                cin >> cats;
                cin >> dogs;
                cin >> voters;

                for (int k = 0; k < voters; k++){
                        String inputString1;
                        String inputString2;
                        cin >> inputString1;
                        cin >> inputString2;
                        int keep = std::stoi(inputString1.at(0));
                        int leave = std::stoi(inputString2.at(0)0;
                        String animal = inputString1.at(0);

                        if (animal == "D") {
                                people.add(people.begin(), new Person(keep, -leave));
                        }
                        else {
                                people.add(people.begin(), new Person(-keep, leave));
                        }

                }

                for (int person1 : people) {
                        for (int person2 : people) {
                                if (person1.keep == person2.leave && person1 != person2) {
                }

                }

        }
}
