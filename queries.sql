-- Part 1: Test it with SQL
SELECT * FROM techjob_persist.employer;
-- Part 2: Test it with SQL
SELECT name FROM techjob_persist.employer where location = "St Louis City";
-- Part 3: Test it with SQL
drop table techjob_persist.job;
-- Part 4: Test it with SQL
select name from trechjob_persist.skill where id not in (select skills_id from job_skill);