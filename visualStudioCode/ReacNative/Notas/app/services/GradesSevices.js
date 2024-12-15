let grades = [
    { subject: 'Matematicas', grade: 9.5 },
    { subject: 'Fisica', grade: 9.2 },
    { subject: 'Historia', grade: 7.5 }
]

export const saveGrade = (grade) => {
    grades.push(grade)
}

export const getGrades = () => {
    return grades
}

export const updateGrade = (nota) => {
    let gradeRetrinved = find(nota.subject)
    if (gradeRetrinved != null) {
        gradeRetrinved = { ...gradeRetrinved, grade: nota.grade }
        const index = grades.findIndex(g => g.subject === nota.subject);
        grades[index] = gradeRetrinved;
    }
    console.log(grades)
}

const find = (subject) => {
    for (let i = 0; i < grades.length; i++) {
        if (grades[i].subject == subject) {
            return grades[i]
        }
    }
    return null
}