
drop table Member cascade constraint;
drop table Genre cascade constraint;
drop table Movie cascade constraint;
drop table Actor cascade constraint;
drop table Profile cascade constraint;
drop table Favorite_genre cascade constraint;
drop table Movies_Watched cascade constraint;
drop table Movie_genre cascade constraint;
drop table Starred_by cascade constraint;

-- Table creations --
create table Member(
    member_ID varchar2(20),
    first_name varchar2(15),
    last_name varchar2 (15),
    card_number varchar2(16),
    exp_date varchar2(10),
        Primary Key (member_ID)
);

create table Genre (
    m_genre varchar2(10),
        Primary Key (m_genre)
);

create table Movie (
    movie_ID varchar2 (20),
    title varchar2 (20),
    movie_year integer,
    producer varchar2(20),
    avg_rating real,
        Primary Key (movie_ID)
);

create table Actor (
    actor_ID varchar2(20),
    first_name varchar2(10),
    last_name varchar2(10),
    Primary Key(actor_ID)
);

create table Profile (
    member_ID varchar2(20),
    profile_name varchar2(20),
    Primary Key (member_ID, profile_name),
    Foreign Key (member_ID) references Member(member_ID)
        on delete cascade
);

create table Favorite_genre (
    member_ID varchar2(20),
    profile_name varchar2(20),
    m_genre varchar2(15),
        Primary Key (member_ID, profile_name, m_genre),
        Foreign Key (member_ID, profile_name) references Profile on delete cascade,
        Foreign Key (m_genre) references Genre (m_genre) on delete cascade
);

create table Movies_Watched (
    member_ID varchar2(20),
    profile_name varchar2(20),
    movie_ID varchar2(20),
    rating integer,
    Primary Key (member_ID, profile_name, movie_ID),
    Foreign Key (member_ID, profile_name) references Profile on delete cascade,
    Foreign Key (movie_ID) references Movie(movie_ID) on delete cascade
);

create table Movie_genre (
    movie_ID varchar2(20),
    m_genre varchar2(15),
    Primary Key (movie_ID, m_genre),
    Foreign Key (movie_ID) references Movie(movie_ID) on delete cascade,
    Foreign Key (m_genre) references Genre(m_genre) on delete cascade
);

create table Starred_by (
    movie_ID varchar2(20),
    actor_ID varchar2(20),
    Primary Key (movie_ID, actor_ID),
    Foreign Key (movie_ID) references Movie(movie_ID) on delete cascade,
    Foreign Key (actor_ID) references Actor(actor_ID) on delete cascade
);

-- Triggers --

-- Keep track of the number of profiles for each member. Cannot exceed 5 profiles per member.
create or replace trigger profileCreation
    before insert on Profile
    for each row
    declare 
        profile_count INTEGER;
        Too_many Exception;
    begin
        select count (member_id) into profile_count
        from Profile
        where member_id = :NEW.member_id;
        
        if profile_count = 5 then
            raise Too_many;
        end if;
    Exception
        when Too_many then
            Raise_application_error(-20001, 'Cannot exceed 5 profiles per account');
end;
/
-- Keep track of valid rating. A valid rating will be from 1 to 5.
create or replace trigger validRating
    before insert or update on Movies_watched
    for each row
    declare 
        invalid Exception;
    begin
        if (:new.rating < 1 or :new.rating > 5) then
            raise invalid;
        end if;
    Exception
        when invalid then
            Raise_application_error(-20001, 'Invalid rating!');
end;
/
-- Update the average rating of the movie whenever a rating is inserted.
create or replace trigger movieRating
for insert on movies_watched
compound trigger 
num_ratings integer;
sum_ratings integer;
before each row is
begin 
    select count(rating) into num_ratings
        from movies_watched
    where movie_id = :NEW.movie_id;
    select sum(rating) into sum_ratings
        from movies_watched
        where movie_id = :NEW.movie_id;
    if num_ratings < 1
        then 
            update movie
            set avg_rating = :NEW.rating
            where movie_id = :NEW.movie_id;
    end if;
    if num_ratings >= 1
        then 
            update movie
            set avg_rating = cast ((sum_ratings + :NEW.rating) / (num_ratings + 1) as decimal (10,2))
            where movie_id = :NEW.movie_id;
    end if;
end before each row;
end movieRating;
/
-- Insertion --
insert into Member values ('dsmith', 'David', 'Smith', '6421876547090904', '08/21');
insert into Member values ('ktran', 'Kevin', 'Tran', '3672822357428625', '01/22');
insert into Member values ('awayne', 'Alex', 'Wayne', '5753078750842374', '08/24');
insert into Member values ('jlynn', 'Joanne', 'Lynn', '1593224122268740', '07/21');
insert into Member values ('cwang', 'Christine', 'Wang', '5239378606960667', '10/23');
insert into Member values ('jdoe', 'Jane', 'Doe', '1192239371593598', '12/24');
insert into Member values ('jbrown', 'John', 'Brown', '3340346226518092', '06/26');

insert into Genre values ('Action');
insert into Genre values ('Adventure');
insert into Genre values ('Comedy');
insert into Genre values ('Drama');
insert into Genre values ('Horror');

insert into Movie values ('rsteel', 'Real Steel', 2011, 'Shawn Levy', null);
insert into Movie values ('tforeigner', 'The Foreigner', 2017, 'Martin Campbell', null);
insert into Movie values ('hman', 'Hit Man', 2007, '20th Century Fox', null);
insert into Movie values ('camerica', 'Captain America', 2011, 'Joe Johnston', null);
insert into Movie values ('nsecurity','National Security', 2003, 'Dennis Dugan', null);
insert into Movie values ('mspy', 'My Spy', 2020, 'Peter Segal', null); 
insert into Movie values ('chearts', 'Chemical Hearts', 2020, 'Richard Tanne', null);
insert into Movie values ('tconjuring', 'The Conjuring', 2013, 'James Wan', null);
insert into Movie values ('tboy', 'The Boy', 2016, 'William Brent Bell', null);
insert into Movie values ('mara', 'Mara', 2017, 'Clive Tonge', null);

insert into Actor values ('hjackman', 'Hugh', 'Jackman');
insert into Actor values ('dgoyo', 'Dakota', 'Goyo');
insert into Actor values ('jchan', 'Jackie', 'Chan');
insert into Actor values ('obrady', 'Orla', 'Brady');
insert into Actor values ('tolyphant', 'Timothy', 'Olyphant');
insert into Actor values ('rknepper', 'Robert', 'Knepper');
insert into Actor values ('cevans', 'Chris', 'Evans');
insert into Actor values ('amackie', 'Anthony', 'Mackie');
insert into Actor values ('szahn', 'Steve', 'Zahn');
insert into Actor values ('mlawrence', 'Martin', 'Lawrence');
insert into Actor values ('dbautista', 'Dave', 'Bautista');
insert into Actor values ('ccoleman', 'Chloe', 'Coleman');
insert into Actor values ('aabrams', 'Austin', 'Abrams');
insert into Actor values ('sjones', 'Sarah', 'Jones');
insert into Actor values ('vfarmiga', 'Vera', 'Farmiga');
insert into Actor values ('pwilson', 'Patrick', 'Wilson');
insert into Actor values ('lcohan', 'Lauren', 'Cohan');
insert into Actor values ('jrussell', 'James', 'Russell');
insert into Actor values ('okurylenko', 'Olga', 'Kurylenko');
insert into Actor values ('jbotet', 'Javier', 'Botet');

insert into Profile values ('dsmith', 'jsmith');
insert into Profile values ('dsmith', 'esmith');
insert into Profile values ('dsmith', 'bsmith');
insert into Profile values ('dsmith', 'lsmith');
insert into Profile values ('ktran', 'etran');
insert into Profile values ('ktran', 'itran');
insert into Profile values ('awayne', 'ewayne');
insert into Profile values ('awayne', 'pwayne');
insert into Profile values ('jlynn', 'jlynn2');
insert into Profile values ('jlynn', 'alynn');
insert into Profile values ('cwang', 'kwang');
insert into Profile values ('cwang', 'jwang');
insert into Profile values ('jdoe', 'pdoe');
insert into Profile values ('jdoe', 'kdoe');
insert into Profile values ('jdoe', 'mdoe');
insert into Profile values ('jbrown', 'bbrown');
insert into Profile values ('jbrown', 'lbrown');

insert into Favorite_genre values ('dsmith', 'jsmith', 'Action');
insert into Favorite_genre values ('dsmith', 'esmith', 'Horror');
insert into Favorite_genre values ('dsmith', 'esmith', 'Comedy');
insert into Favorite_genre values ('dsmith', 'bsmith', 'Drama');
insert into Favorite_genre values ('dsmith', 'lsmith', 'Comedy');
insert into Favorite_genre values ('ktran', 'etran', 'Comedy');
insert into Favorite_genre values ('ktran', 'itran', 'Adventure');
insert into Favorite_genre values ('awayne', 'ewayne', 'Drama');
insert into Favorite_genre values ('awayne', 'pwayne', 'Action');
insert into Favorite_genre values ('jlynn', 'alynn', 'Horror');
insert into Favorite_genre values ('jlynn', 'jlynn2', 'Action');
insert into Favorite_genre values ('cwang', 'kwang', 'Comedy');
insert into Favorite_genre values ('cwang', 'jwang', 'Adventure');
insert into Favorite_genre values ('jdoe', 'pdoe', 'Horror');
insert into Favorite_genre values ('jdoe', 'kdoe', 'Action');
insert into Favorite_genre values ('jdoe', 'mdoe', 'Adventure');
insert into Favorite_genre values ('jbrown', 'bbrown', 'Drama');
insert into Favorite_genre values ('jbrown', 'lbrown', 'Action');

insert into Movie_genre values ('rsteel', 'Action');
insert into Movie_genre values ('tforeigner', 'Action');
insert into Movie_genre values ('hman', 'Adventure');
insert into Movie_genre values ('camerica', 'Adventure');
insert into Movie_genre values ('nsecurity', 'Comedy');
insert into Movie_genre values ('mspy', 'Comedy');
insert into Movie_genre values ('chearts', 'Drama');
insert into Movie_genre values ('tconjuring', 'Horror');
insert into Movie_genre values ('tboy', 'Horror');
insert into Movie_genre values ('mara', 'Horror');

insert into Starred_by values ('rsteel', 'hjackman');
insert into Starred_by values ('rsteel', 'dgoyo');
insert into Starred_by values ('tforeigner', 'jchan');
insert into Starred_by values ('tforeigner', 'obrady');
insert into Starred_by values ('hman', 'tolyphant');
insert into Starred_by values ('hman', 'rknepper');
insert into Starred_by values ('camerica', 'cevans');
insert into Starred_by values ('camerica', 'amackie');
insert into Starred_by values ('nsecurity', 'szahn');
insert into Starred_by values ('nsecurity', 'mlawrence');
insert into Starred_by values ('mspy', 'dbautista');
insert into Starred_by values ('mspy', 'ccoleman');
insert into Starred_by values ('chearts', 'aabrams');
insert into Starred_by values ('chearts', 'sjones');
insert into Starred_by values ('tconjuring', 'vfarmiga');
insert into Starred_by values ('tconjuring', 'pwilson');
insert into Starred_by values ('tboy', 'lcohan');
insert into Starred_by values ('tboy', 'jrussell');
insert into Starred_by values ('mara', 'okurylenko');
insert into Starred_by values ('mara', 'jbotet');

insert into Movies_watched values ('dsmith', 'jsmith', 'rsteel', 3);
insert into Movies_watched values ('dsmith', 'esmith', 'rsteel',4);
insert into Movies_watched values ('dsmith', 'esmith', 'tforeigner',4);
insert into Movies_watched values ('dsmith', 'jsmith', 'chearts',4);
insert into Movies_watched values ('ktran', 'etran', 'rsteel', 2);
insert into Movies_watched values ('ktran', 'itran', 'camerica', 4);
insert into Movies_watched values ('ktran', 'etran', 'hman', 4);
insert into Movies_watched values ('ktran', 'etran', 'mspy', 1);
insert into Movies_watched values ('awayne', 'ewayne', 'nsecurity', 4);
insert into Movies_watched values ('awayne', 'pwayne', 'mspy', 2);
insert into Movies_watched values ('awayne', 'pwayne', 'tboy', 3);
insert into Movies_watched values ('jlynn', 'jlynn2', 'tconjuring', 4);
insert into Movies_watched values ('jlynn', 'alynn', 'tboy', 4);
insert into Movies_watched values ('cwang', 'kwang', 'mara', 3);
insert into Movies_watched values ('cwang', 'jwang', 'mara', 2);
insert into Movies_watched values ('cwang', 'jwang', 'rsteel', 5);
insert into Movies_watched values ('jdoe', 'pdoe', 'mara',3);
insert into Movies_watched values ('jdoe', 'pdoe', 'rsteel', 5);
insert into Movies_watched values ('jdoe', 'kdoe', 'camerica',3);
insert into Movies_watched values ('jdoe', 'mdoe', 'chearts',4);
insert into Movies_watched values ('jdoe', 'kdoe', 'chearts',2);
insert into Movies_watched values ('jdoe', 'mdoe', 'hman',1);
insert into Movies_watched values ('jbrown', 'bbrown', 'nsecurity',4);
insert into Movies_watched values ('jbrown', 'lbrown', 'tforeigner',3);
insert into Movies_watched values ('jbrown', 'lbrown', 'nsecurity', 5);
insert into Movies_watched values ('jbrown', 'bbrown', 'tconjuring', 5);

select * from Member;
select * from Genre;
select * from Movie;
select * from Actor;
select * from Profile;
select * from Favorite_genre;
select * from Movies_watched;
select * from Movie_genre;
select * from Starred_by;