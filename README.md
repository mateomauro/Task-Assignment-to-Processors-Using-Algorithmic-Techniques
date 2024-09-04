
Task Assignment to Processors Using Algorithmic Techniques
Important Note
The first part must be opened separately from the second part. If both parts are opened together, the .CSV file paths will not be recognized.

Description
This project was developed as part of the Programming 3 course in the Technical University Degree in Application Development (TUDAI) at UNICEN. The main objective is to solve problems related to task assignment to processors using algorithmic techniques such as Backtracking and Greedy.

Project Context
The practical coursework proposes solving various problems associated with a simplified context of processors and tasks.

First Part
Implemented Services:
Retrieve task information by its ID.
List critical or non-critical tasks.
Filter tasks by priority levels.
Second Part
Task Assignment:
Constraints:
Maximum of 2 critical tasks per processor.
Execution time limit for non-cooled processors.
Techniques used:
Backtracking: Optimal solution with state generation analysis.
Greedy: Approximate solution with candidate consideration analysis.
Project Structure
Processors.csv: <processor_id>;<processor_code>;<is_cooled?>;<year_of_operation>
Tasks.csv: <task_id>;<task_name>;<execution_time>;<is_critical?>;<priority_level>