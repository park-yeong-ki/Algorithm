SELECT 
CASE 
    WHEN 
        SKILL_CODE & (
            SELECT SUM(CODE)
            FROM SKILLCODES
            WHERE CATEGORY = 'Front End'
        ) != 0 
        AND
        SKILL_CODE & (
            SELECT CODE
            FROM SKILLCODES
            WHERE NAME = 'Python'
        ) != 0
    THEN
        'A'
    WHEN
        SKILL_CODE & (
            SELECT CODE
            FROM SKILLCODES
            WHERE NAME = 'C#'
        ) != 0
    THEN
        'B'
    ELSE
        'C'
END
    AS GRADE, ID, EMAIL
FROM DEVELOPERS
WHERE 
(SKILL_CODE & (
    SELECT SUM(CODE)
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
) != 0 
AND
SKILL_CODE & (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'Python'
) != 0)
OR
(SKILL_CODE & (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'C#'
) != 0)
OR
(SKILL_CODE & (
    SELECT SUM(CODE)
    FROM SKILLCODES
    WHERE CATEGORY = 'Front End'
) != 0)
ORDER BY GRADE, ID;